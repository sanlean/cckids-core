package io.github.sanlean.cckids.domain.result

sealed class DomainError {

    object Cancellation: DomainError()

    object NetworkUnavailable : DomainError()

    object Unauthorized : DomainError()

    object Forbidden : DomainError()

    object NotFound : DomainError()

    object Timeout : DomainError()

    object ServerError : DomainError()

    data class Validation(val message: String) : DomainError()

    data class Unknown(val cause: Throwable? = null) : DomainError()
}