# Copyright (C) 2017 XXX

inherit bin_package

DESCRIPTION = "Pre-built Node.js. This recipe is used only to fetch and verify deb-file."
LICENSE = "MIT"

PROVIDES = "nodejs"

PV = "binary"
REAL_PV = "8.6.0"
PR = "r0"
SRC_URI[md5sum] = "6fbb6ccf85331583f1a0012fe0348572"
SRC_URI[sha256sum] = "0e606a78198579dc9f4eee7ea91fd6e7900d43d7a8d7eab2c1b68c9f9506ca90"

DEB_FILENAME = "nodejs_${REAL_PV}-1nodesource1_armhf.deb"

SRC_URI = " \
https://deb.nodesource.com/node_8.x/pool/main/n/nodejs/${DEB_FILENAME}"

LIC_FILES_CHKSUM = "file://LICENSE;md5=e4e4a75bcfb6e9439516b90dd805a1a7"

SRC_URI += "file://foo.nodejs"


do_install_append () {
    install -d -m 0755 ${D}/usr/share/
    install -m 0644 ${WORKDIR}/foo.nodejs ${D}/usr/share/
}

FILES_${PN} += "/usr/share/foo.nodejs"
