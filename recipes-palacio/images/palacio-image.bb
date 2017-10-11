# Copyright (C) 2017 XXX
# Released under the MIT license (see COPYING.MIT for the terms)

LICENSE = "MIT"

do_rootfs[depends] += "virtual/kernel:do_populate_sysroot"

require recipes-core/images/core-image-base.bb
require recipes-rk/images/rk-image-multimedia.bb

IMAGE_FEATURES_remove = " splash"

IMAGE_FEATURES_append = " tools-debug"


COMMON_INSTALL = " \
	qtbase	\
	qtdeclarative \
	qtmultimedia \
	qtsvg \
	qtimageformats \
"


QT_DEMOS = " \
	qt5everywheredemo \
"

IMAGE_INSTALL += " \
	kernel-modules \
	rtl8723bs-firmware \
	wireless-tools \
	wpa-supplicant \
	${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'qtwayland', '', d)} \
	${COMMON_INSTALL} \
	${QT_DEMOS} \
	packagegroup-fonts-truetype \
	sqlite3 \
	dhcpcd \
	ntp \
	ca-certificates \
	nodejs \
"

#
# REVISIT Dev tools. Should we move'em to separate sub-image?
#
IMAGE_INSTALL += " \
	rsync \
	i2c-tools \
	usbutils \
	lsof \
	mmc-utils \
"

IMAGE_FEATURE_append = " \
	ssh-server-dropbear \
"

FEATURE_INSTALL_remove = " \
	apt \
"

IMAGE_INSTALL_remove = " \
	autostart \
"

# XXX skip the following package due to build problems
#IMAGE_INSTALL_remove = " \
#	gstreamer1.0-libav \
#"

#
# Install prebuilt Node binaries
#
NODEJS_DEB_FILENAME = "nodejs_8.6.0-1nodesource1_armhf.deb"

#LIC_FILES_CHKSUM = "file://NODEJS_LICENSE;md5=e4e4a75bcfb6e9439516b90dd805a1a7"


install_nodejs_deb() {
    echo "IMAGE_ROOTFS: ${IMAGE_ROOTFS}"
    ${STAGING_BINDIR_NATIVE}/dpkg-deb -x ${CO_DIR}/${NODEJS_DEB_FILENAME} ${IMAGE_ROOTFS}
    #${STAGING_BINDIR_NATIVE}/dpkg --force-architecture  --root=${IMAGE_ROOTFS}/ --admindir=${IMAGE_ROOTFS}/var/lib/dpkg/ --ignore-depends=libgcc1 --ignore-depends=python-minimal --force-depends -i ${CO_DIR}/${NODEJS_DEB_FILENAME}
}

ROOTFS_POSTPROCESS_COMMAND_append = " install_nodejs_deb; "
