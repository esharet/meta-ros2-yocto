# Copyright (C) 2022 esharet <esharet@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "example of git recipe"
HOMEPAGE = "https://github.com/esharet/follow"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://github.com/esharet/gazebo_visual_united;protocol=https;branch=main"
SRCREV = "c4f1971fb92962949a4b4dc9c5847d912a393a6a"

S="${WORKDIR}/git"

do_install() {
    install -d ${D}${ROOT_HOME}/projects/gazebo_unity_git/gazebo_unity_messages
    cp -r ${S}/gazebo_unity_messages ${D}${ROOT_HOME}/projects/gazebo_unity_git/gazebo_unity_messages
}
FILES_${PN} += "${ROOT_HOME}/projects/gazebo_unity_git/gazebo_unity_messages"
