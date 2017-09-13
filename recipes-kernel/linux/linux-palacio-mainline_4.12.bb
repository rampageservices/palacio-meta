# Copyright (C) 2017 Romain Perier
# Copyright (C) 2017 Eddie Cai
# Released under the MIT license (see COPYING.MIT for the terms)

require recipes-kernel/linux/linux-yocto.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/torvalds/linux.git"
SRC_URI += "file://defconfig"

# FROMLIST PATCH
SRC_URI += " \
file://0000-Add-MALI-driver-r17p0-01rel0.patch \
file://0001-Midgard-daptation-to-Linux-4.10.0-rcX-signatures.patch \
file://0001-Readaptation-of-Rockchip-DRM-patches-provided-by-ARM.patch \
file://0002-Integrate-the-Mali-GPU-address-to-the-rk3288-and-rk3.patch \
file://0002-UMP-Adapt-get_user_pages-calls.patch \
file://0003-Post-Mali-Kernel-device-drivers-modifications.patch \
file://0003-Renamed-Kernel-DMA-Fence-structures-and-functions.patch \
file://0004-Few-modifications-after-v4.11-headers-and-signatures.patch \
file://0004-Post-Mali-UMP-integration.patch \
file://0005-ARM-dts-rockchip-fix-the-regulator-s-voltage-range-o.patch \
file://0005-Using-the-new-header-on-4.12-kernels-for-copy_-_user.patch \
file://0007-Adaptation-ARM-dts-rockchip-add-the-MiQi-board-s-fan.patch \
file://0008-ARM-dts-rockchip-add-support-for-1800-MHz-operation-.patch \
file://0009-clk-rockchip-add-all-known-operating-points-to-the-a.patch \
file://0010-Readapt-ARM-dts-rockchip-miqi-add-turbo-mode-operati.patch \
file://0011-arm-dts-Adding-and-enabling-VPU-services-addresses-f.patch \
file://0012-Export-rockchip_pmu_set_idle_request-for-out-of-tree.patch \
file://0013-clk-rockchip-rk3288-prefer-vdpu-for-vcodec-clock-sou.patch \
file://0014-ARMbian-RK3288-DTSI-changes.patch \
file://0015-Enabling-Tinkerboard-s-Wifi-Third-tentative.patch \
file://0016-Added-support-for-Tinkerboard-s-SPI-interface.patch \
file://0017-Testing-DTS-changes-in-order-to-resolve-bug-8.patch \
file://0018-Added-debug-messages-to-check-the-Bluetooth-Coexiste.patch \
file://0019-The-ASUS-Tinkerboard-reboot-patch.patch \
file://0020-Common-RK3288-DTSI-additions-by-ARMbian.patch \
file://0100-First-Mali-integration-test-for-ASUS-Tinkerboards.patch \
file://0200-The-Tinkerboard-DTS-file-maintained-by-TonyMac32-and.patch \
file://0300-Adding-Mali-Midgard-and-VCodec-support-to-Firefly-RK.patch \
"

SRCREV = "v4.12"
LINUX_VERSION = "4.12"
# Override local version in order to use the one generated by linux build system
# And not "yocto-standard"
LINUX_VERSION_EXTENSION = ""
PR = "r1"
PV = "${LINUX_VERSION}"

# Include only supported boards for now
COMPATIBLE_MACHINE = "(palacio-rk3288)"

KCONFIG_MODE = "--alldefconfig"
