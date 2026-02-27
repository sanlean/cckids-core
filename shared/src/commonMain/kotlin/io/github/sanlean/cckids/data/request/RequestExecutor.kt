package io.github.sanlean.cckids.data.request

import io.github.sanlean.cckids.domain.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext

suspend fun <T> executeRequest(
    dispatcher: CoroutineDispatcher,
    block: suspend () -> T
): Result<T> {
    val execution: suspend CoroutineScope.() -> Result<T> = {
        try {
            Result.Success(block())
        } catch (throwable: Throwable) {
            Result.Failure(throwable.toDomainError())
        }
    }
    return withContext<Result<T>>(dispatcher, execution)
}