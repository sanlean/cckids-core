package io.github.sanlean.cckids

class JSPlatform: Platform {
    override val target: TargetType = TargetType.JS

}

actual fun getPlatform(): Platform = JSPlatform()