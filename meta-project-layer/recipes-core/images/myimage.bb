# Copyright (C) 2022 esharet <esharet@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)
require ${TOPDIR}/../meta-external/poky/meta/recipes-core/images/core-image-base.bb

SUMMARY = ""

IMAGE_LINGUAS = "zh-cn en-us"
IMAGE_NAME = "myimage" 
IMAGE_INSTALL_append = " packagegroup-myutils " 



