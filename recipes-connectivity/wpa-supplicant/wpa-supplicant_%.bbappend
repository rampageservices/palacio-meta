FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://wpa_supplicant.service"

FILES_${PN} += "${sysconfdir}/systemd/system/multi-user.target.wants/wpa_supplicant.service"

do_install_append () {
	if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
		install -d ${D}${sysconfdir}/systemd/system/multi-user.target.wants
		install -m 0644 ${WORKDIR}/wpa_supplicant.service ${D}${systemd_system_unitdir}
		ln -sf ${systemd_system_unitdir}/wpa_supplicant.service ${D}${sysconfdir}/systemd/system/multi-user.target.wants/wpa_supplicant_wlan0.service
	fi
}
