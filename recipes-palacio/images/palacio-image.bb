# Copyright (C) 2017 XXX
# Released under the MIT license (see COPYING.MIT for the terms)

LICENSE = "MIT"

do_rootfs[depends] += "virtual/kernel:do_populate_sysroot"

#require recipes-rk/images/rk-image-base.bb
require recipes-rk/images/rk-image-multimedia.bb

IMAGE_FEATURES_remove = " splash"


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
	${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'qtwayland', '', d)} \
	${COMMON_INSTALL} \
	${QT_DEMOS} \
	autostart \
	packagegroup-fonts-truetype \
	sqlite3 \
	dhcpcd \
	ca-certificates \
	 \
"

#
# REVISIT Dev tools. Should we move'em to separate sub-image?
#
IMAGE_INSTALL += " \
	rsync \
	i2c-tools \
	usbutils \
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
