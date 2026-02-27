package io.github.sanlean.cckids.domain.usecase

import io.github.sanlean.cckids.domain.model.Material
import io.github.sanlean.cckids.domain.repository.AulaRepository
import io.github.sanlean.cckids.domain.result.Result

class ListAulaMateriaisUseCase(private val repository: AulaRepository) {
    suspend operator fun invoke(aulaId: String): Result<List<Material>> = repository.listMateriaisDaAula(aulaId)
}
