package io.github.sanlean.cckids.domain.usecase

import io.github.sanlean.cckids.domain.model.Aula
import io.github.sanlean.cckids.domain.repository.AulaRepository
import io.github.sanlean.cckids.domain.result.Result

class GetAulaUseCase(private val repository: AulaRepository) {
    suspend operator fun invoke(id: String): Result<Aula> = repository.getAula(id)
}
