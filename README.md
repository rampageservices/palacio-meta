# Setup environment using Docker


In order to make reproducible environment and predictable build reults, I used Docker.
It is based on **build-yocto** subproject of **easy-build**.

More information:
<https://hub.docker.com/r/gmacario/build-yocto/>

I made some modifications to ease the access to source code and artifacts from both Docker container and host system. Main modification is changing user id of build user to match UID of user of the host system.

Creating docker image:
```shell
docker build -t gmacario/build-yocto build-yocto/
```

Launch container:
```shell
docker run -ti --volume=${PWD}:/home/build/shared --tmpfs=/tmp gmacario/build-yocto
```

Configuring build configuration inside the container:
```shell
cd shared
repo init -u git@github.com:rampageservices/palacio-manifest.git -b palacio
repo sync
MACHINE="palacio-rk3288" DISTRO="palacio-none" . setup-environment -b build
```
Open conf/bblayers.conf in your favorite editor and meta-palacio layer to BBLAYERS.
It should look like this:
```
BBLAYERS = " \
  ${BSPDIR}/sources/poky/meta \
  ${BSPDIR}/sources/poky/meta-poky \
  \
  ${BSPDIR}/sources/meta-openembedded/meta-oe \
  ${BSPDIR}/sources/meta-openembedded/meta-multimedia \
  ${BSPDIR}/sources/meta-openembedded/meta-networking \
  ${BSPDIR}/sources/meta-openembedded/meta-filesystems \
  ${BSPDIR}/sources/meta-openembedded/meta-python \
  ${BSPDIR}/sources/meta-qt5 \
  \
  ${BSPDIR}/sources/meta-rockchip \
  ${BSPDIR}/sources/meta-rockchip-extra \
  ${BSPDIR}/sources/meta-palacio \
"
```

Build the image:
```shell
bitbake palacio-image
```

# Flashing the image

```shell
dd if=./shared/rk-yocto-bsp/build-wayland/tmp/deploy/images/palacio-rk3288/palacio-image-palacio-rk3288-gpt.img of=/dev/mmcblk0 bs=4M
```

# Known issues

* bitbake complains about host OS version (Ubuntu 14.04 LTS).
* Docker mounts /tmp with noexec flag. This causes build problems with some software

---


