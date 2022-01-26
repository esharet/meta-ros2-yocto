# Copyright (C) 2022 esharet <esharet@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = ""

inherit packagegroup

RDEPENDS_${PN} = " \ 
dropbear \
vim \
libgpiod \
libgpiod-tools \
python3 \
python3-dev"

