package io.github.sanlean.cckids.data.request

import io.github.sanlean.cckids.domain.result.DomainError
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.ResponseException
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.io.IOException
import kotlin.coroutines.cancellation.CancellationException

internal fun Throwable.toDomainError(): DomainError {

    return when (this) {

        is CancellationException -> throw this

        is TimeoutCancellationException ->
            DomainError.Timeout

        is ClientRequestException ->
            when (response.status) {
                HttpStatusCode.Unauthorized -> DomainError.Unauthorized
                HttpStatusCode.Forbidden -> DomainError.Forbidden
                HttpStatusCode.NotFound -> DomainError.NotFound
                else -> DomainError.Validation(response.status.description)
            }

        is ServerResponseException ->
            DomainError.ServerError

        is ResponseException ->
            DomainError.ServerError

        is IOException ->
            DomainError.NetworkUnavailable

        else ->
            DomainError.Unknown(this)
    }
}