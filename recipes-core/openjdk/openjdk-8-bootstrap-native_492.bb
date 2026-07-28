SUMMARY = "Prebuilt Temurin JDK 8 used as boot JDK for openjdk-8-native"
HOMEPAGE = "https://adoptium.net"
LICENSE = "GPL-2.0-with-classpath-exception"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3e0b59f8fac05c3c03d4a26bbda13f8f"

# The GNU Classpath/JamVM/IcedTea7 source bootstrap has no aarch64 port,
# so boot OpenJDK 8 from an upstream binary snapshot instead (the same
# approach oe-core uses for rust).
BOOTSTRAP_BUILD = "b09"
BOOTSTRAP_ARCH = "${@{'x86_64': 'x64'}.get(d.getVar('BUILD_ARCH'), d.getVar('BUILD_ARCH'))}"

SRC_URI = "https://github.com/adoptium/temurin8-binaries/releases/download/jdk8u${PV}-${BOOTSTRAP_BUILD}/OpenJDK8U-jdk_${BOOTSTRAP_ARCH}_linux_hotspot_8u${PV}${BOOTSTRAP_BUILD}.tar.gz"
SRC_URI[sha256sum] = "${@{'aarch64': '3c2253b986909c20f79d6de7a0cb957f89c243df57615897836046e24d2e5257', 'x64': 'da257f161d7f8c6ca5b0e5d9e4090f65ac28c5e398072e68b8ae87988b1d1a2e'}[d.getVar('BOOTSTRAP_ARCH')]}"

S = "${UNPACKDIR}/jdk8u${PV}-${BOOTSTRAP_BUILD}"

COMPATIBLE_HOST = "(x86_64|aarch64).*-linux"

inherit native

INHIBIT_DEFAULT_DEPS = "1"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    install -d ${D}${libdir}/jvm/openjdk-8-bootstrap
    cp -rp ${S}/. ${D}${libdir}/jvm/openjdk-8-bootstrap/
}
