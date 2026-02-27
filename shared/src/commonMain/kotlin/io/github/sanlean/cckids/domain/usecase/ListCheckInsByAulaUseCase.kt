package io.github.sanlean.cckids.domain.usecase

import io.github.sanlean.cckids.domain.model.CheckIn
import io.github.sanlean.cckids.domain.repository.CheckInRepository
import io.github.sanlean.cckids.domain.result.Result

class ListCheckInsByAulaUseCase(private val repository: CheckInRepository) {
    suspend operator fun invoke(aulaId: String): Result<List<CheckIn>> = repository.listByAula(aulaId)
}
