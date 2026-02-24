package io.github.sanlean.cckids

class AndroidPlatform : Platform {
    override val target: TargetType = TargetType.ANDROID
}

actual fun getPlatform(): Platform = AndroidPlatform()