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
mkdir -p shared/yocto 2>/dev/null
docker run -ti --volume=${PWD}/shared:/home/build/shared --tmpfs=/tmp gmacario/build-yocto
```

Running build process inside the container:
```shell
cd shared/yocto
repo init -u https://github.com/rockchip-linux/manifests -b yocto
repo sync
MACHINE="tinker-rk3288" DISTRO="rk-wayland" . setup-environment -b build-wayland/
bitbake qt5-image
bitbake palacio-image
```

# Flashing the image

```shell
dd if=./shared/rk-yocto-bsp/build-wayland/tmp/deploy/images/tinker-rk3288/qt5-image-tinker-rk3288-gpt.img of=/dev/mmcblk0 bs=4M
```

# Known issues

* bitbake complains about host OS version (Ubuntu 14.04 LTS).
* Docker mounts /tmp with noexec flag. This causes build problems with some software

---


