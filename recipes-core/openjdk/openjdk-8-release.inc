require openjdk-8-release-common.inc

# We do not yet work for aarch64, and we make sure
# to use the aarch32 port for ARMv7.
COMPATIBLE_HOST = "^(?!(aarch64)).*$"
COMPATIBLE_HOST:armv7a = "^$"
COMPATIBLE_HOST:armv7ve = "^$"

# tag jdk8u${PV}-ga
SRCREV = "236b800cf26b8e138c925334371681e6b0a099ab"
