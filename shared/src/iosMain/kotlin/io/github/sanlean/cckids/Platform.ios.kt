package io.github.sanlean.cckids

class IOSPlatform: Platform {
    override val target: TargetType = TargetType.IOS
}

actual fun getPlatform(): Platform = IOSPlatform()