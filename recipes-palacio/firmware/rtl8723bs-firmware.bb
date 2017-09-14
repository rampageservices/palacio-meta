# Copyright (C) 2017 XXX

inherit allarch

DESCRIPTION = "Realtek 8732BS firmware"
LICENSE = "CLOSED"

PROVIDES = "rtl8723bs_firmware"

SRC_URI=" \
    file://rtl8723bs_ap_wowlan.bin \
    file://rtl8723bs_bt.bin \
    file://rtl8723bs_nic.bin \
    file://rtl8723bs_wowlan.bin \
"


FILES_${PN} = "/lib/firmware/rtlbt/*"

do_install() {
    install -d ${D}/lib/firmware/rtlbt
    install -m 0644 ${WORKDIR}/rtl8723bs_ap_wowlan.bin ${D}/lib/firmware/rtlbt
    install -m 0644 ${WORKDIR}/rtl8723bs_bt.bin ${D}/lib/firmware/rtlbt
    install -m 0644 ${WORKDIR}/rtl8723bs_nic.bin ${D}/lib/firmware/rtlbt
    install -m 0644 ${WORKDIR}/rtl8723bs_wowlan.bin ${D}/lib/firmware/rtlbt
}
