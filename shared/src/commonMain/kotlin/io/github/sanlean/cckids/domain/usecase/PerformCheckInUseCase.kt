package io.github.sanlean.cckids.domain.usecase

import io.github.sanlean.cckids.domain.model.CheckIn
import io.github.sanlean.cckids.domain.repository.CheckInRepository
import io.github.sanlean.cckids.domain.result.Result

class PerformCheckInUseCase(private val repository: CheckInRepository) {
    suspend operator fun invoke(
        aulaId: String,
        alunoId: String,
        professorId: String,
        dateTimeIso: String? = null
    ): Result<CheckIn> = repository.performCheckIn(aulaId, alunoId, professorId, dateTimeIso)
}
