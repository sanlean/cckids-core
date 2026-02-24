package io.github.sanlean.cckids.domain.result

sealed class Result<out T> {

    data class Success<T>(val data: T) : Result<T>()

    data class Failure(val error: DomainError) : Result<Nothing>()

    val isSuccess: Boolean
        get() = this is Success<T>

    val isFailure: Boolean
        get() = this is Failure
}