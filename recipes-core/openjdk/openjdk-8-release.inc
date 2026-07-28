require openjdk-8-release-common.inc

# aarch64 and ARMv7 use their own port includes; OpenJDK 8 predates
# RISC-V and never had a port for it.
COMPATIBLE_HOST = "^(?!(aarch64|riscv)).*$"
COMPATIBLE_HOST:armv7a = "^$"
COMPATIBLE_HOST:armv7ve = "^$"

# tag jdk8u${PV}-ga
SRCREV = "236b800cf26b8e138c925334371681e6b0a099ab"
