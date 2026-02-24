package io.github.sanlean.cckids

class LinuxPlatform: Platform {
    override val target: TargetType = TargetType.LINUX
}

actual fun getPlatform(): Platform = LinuxPlatform()