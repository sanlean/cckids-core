package io.github.sanlean.cckids.thread

import kotlinx.coroutines.CoroutineDispatcher

interface ThreadingPolicy {
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
}