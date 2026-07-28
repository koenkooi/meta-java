require openjdk-8-release-common.inc

COMPATIBLE_HOST = "^$"
COMPATIBLE_HOST:armv7a = "arm"
COMPATIBLE_HOST:armv7ve = "arm"

OPENJDK_GIT_REPO = "aarch32-port-jdk8u"
# tag jdk8u${PV}-ga-aarch32-20220131
SRCREV = "42926ba2d250ae79392286187b459dc2dc37228e"

PACKAGECONFIG:append:armv7a = " client"
PACKAGECONFIG:append:armv7ve = " client"

do_install:append() {
    if [ -d ${D}${JDK_HOME} ] ; then
        sed -e 's,^\(-server ERROR$\),# \1,' \
            -e 's,^-client IGNORE,-client ERROR,' \
            -e 's,ALIASED_TO -server,ALIASED_TO -client,' \
            -i ${D}${JDK_HOME}/jre/lib/${JDK_ARCH}/jvm.cfg
    fi
    if [ -d ${D}${JRE_HOME} ] ; then
        sed -e 's,^\(-server ERROR$\),# \1,' \
            -e 's,^-client IGNORE,-client ERROR,' \
            -e 's,ALIASED_TO -server,ALIASED_TO -client,' \
            -i ${D}${JRE_HOME}/lib/${JDK_ARCH}/jvm.cfg
    fi
}

JDK_ARCH:armv7a = "aarch32"
JDK_ARCH:armv7ve = "aarch32"
