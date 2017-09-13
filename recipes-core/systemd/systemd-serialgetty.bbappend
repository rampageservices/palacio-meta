# Disable tty on ttyS2
do_install_append() {
	rm ${D}${sysconfdir}/systemd/system/getty.target.wants/serial-getty@ttyS2.service
}
