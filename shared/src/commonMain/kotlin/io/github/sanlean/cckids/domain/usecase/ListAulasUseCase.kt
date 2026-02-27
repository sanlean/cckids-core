package io.github.sanlean.cckids.domain.usecase

import io.github.sanlean.cckids.domain.model.AulaResumo
import io.github.sanlean.cckids.domain.repository.AulaRepository
import io.github.sanlean.cckids.domain.result.Result

class ListAulasUseCase(private val repository: AulaRepository) {
    suspend operator fun invoke(fromIso: String? = null, toIso: String? = null): Result<List<AulaResumo>> =
        repository.listAulas(fromIso, toIso)
}