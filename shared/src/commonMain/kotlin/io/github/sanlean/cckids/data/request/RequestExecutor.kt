package io.github.sanlean.cckids.data.request

import io.github.sanlean.cckids.domain.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlin.coroutines.cancellation.CancellationException

suspend fun <T> executeRequest(
    dispatcher: CoroutineDispatcher,
    block: suspend () -> T
): Result<T> = withContext(dispatcher) {
    try {
        Result.Success(block())
    } catch (cancellation: CancellationException) {
        throw cancellation
    } catch (throwable: Throwable) {
        Result.Failure(throwable.toDomainError())
    }
}