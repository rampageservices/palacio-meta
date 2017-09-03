# Copyright (C) 2017
# Released under the MIT license (see COPYING.MIT for the terms)

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

#SRC_URI += "file://defconfig"
SRC_URI += "file://v5-1-2-doc-cx2092x-Add-DT-bingings-doc-for-CX2092X-DSP.patch"
SRC_URI += "file://v5-2-2-ASoC-Add-support-for-Conexant-CX2092X-DSP.patch"
