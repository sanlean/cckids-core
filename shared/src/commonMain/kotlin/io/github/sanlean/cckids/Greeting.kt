package io.github.sanlean.cckids

class Greeting {
    private val platform: Platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.target}!"
    }
}