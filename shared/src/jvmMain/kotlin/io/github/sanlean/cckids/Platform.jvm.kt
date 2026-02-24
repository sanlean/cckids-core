package io.github.sanlean.cckids

class JVMPlatform: Platform {
    override val target: TargetType = TargetType.JVM
}

actual fun getPlatform(): Platform = JVMPlatform()