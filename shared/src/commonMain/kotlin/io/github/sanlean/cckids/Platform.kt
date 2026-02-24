package io.github.sanlean.cckids

interface Platform {
    val target: TargetType
}

expect fun getPlatform(): Platform