package io.github.sanlean.cckids.domain.usecase

import io.github.sanlean.cckids.domain.model.Session
import io.github.sanlean.cckids.domain.repository.AuthRepository
import io.github.sanlean.cckids.domain.result.Result

class LoginUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String): Result<Session> =
        repository.login(email, password)
}
