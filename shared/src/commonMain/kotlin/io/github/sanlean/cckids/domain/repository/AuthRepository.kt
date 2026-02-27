package io.github.sanlean.cckids.domain.repository

import io.github.sanlean.cckids.domain.model.Session
import io.github.sanlean.cckids.domain.result.Result

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<Session>
}
