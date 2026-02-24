package io.github.sanlean.cckids

class WindowsPlatform: Platform {
    override val target: TargetType = TargetType.WINDOWS
}

actual fun getPlatform(): Platform = WindowsPlatform()
