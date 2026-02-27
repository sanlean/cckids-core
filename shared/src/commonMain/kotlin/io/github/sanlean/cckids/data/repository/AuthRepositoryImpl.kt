package io.github.sanlean.cckids.data.repository

import io.github.sanlean.cckids.data.dto.LoginRequestDto
import io.github.sanlean.cckids.data.dto.SessionDto
import io.github.sanlean.cckids.data.mapper.toDomain
import io.github.sanlean.cckids.data.request.executeRequest
import io.github.sanlean.cckids.domain.model.Session
import io.github.sanlean.cckids.domain.repository.AuthRepository
import io.github.sanlean.cckids.domain.result.Result
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.coroutines.CoroutineDispatcher

class AuthRepositoryImpl(
    private val client: HttpClient,
    private val dispatcher: CoroutineDispatcher
) : AuthRepository {
    override suspend fun login(email: String, password: String): Result<Session> =
        executeRequest(dispatcher) {
            val dto: SessionDto = client.post(ApiRoutes.LOGIN) {
                setBody(LoginRequestDto(email, password))
            }.body()
            dto.toDomain()
        }
}
