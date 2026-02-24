package io.github.sanlean.cckids

class MacOSPlatform: Platform {
    override val target: TargetType = TargetType.MACOS
}

actual fun getPlatform(): Platform = MacOSPlatform()