# See https://git.yoctoproject.org/poky/tree/meta/files/common-licenses
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://git@github.com/cu-ecen-aeld/assignments-3-and-later-RobBobRobJK.git;protocol=ssh;branch=main"

PV = "1.0+git${SRCPV}"
SRCREV = "a90949ab657a09876ed395bdf8243d3e8cc27d9b"

# Build from the "server" directory of the assignments repo
S = "${WORKDIR}/git/server"

# Install the start/stop script as a boot-time init script
inherit update-rc.d
INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME:${PN} = "aesdsocket-start-stop"
INITSCRIPT_PARAMS:${PN} = "defaults 99"

FILES:${PN} += "${bindir}/aesdsocket ${sysconfdir}/init.d/aesdsocket-start-stop"

do_configure () {
    :
}

do_compile () {
    oe_runmake
}

do_install () {
    install -d ${D}${bindir}
    install -m 0755 ${S}/aesdsocket ${D}${bindir}/aesdsocket
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${S}/aesdsocket-start-stop ${D}${sysconfdir}/init.d/aesdsocket-start-stop
}
