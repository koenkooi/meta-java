# -native runs on the build machine, so pick the port matching BUILD_ARCH
# (the target-driven overrides in openjdk-8_${PV}.bb don't apply here).
INC_FILE_SUFFIX = "${@'-aarch64' if d.getVar('BUILD_ARCH') == 'aarch64' else ''}"
require openjdk-8-release${INC_FILE_SUFFIX}.inc
require openjdk-8-native.inc
