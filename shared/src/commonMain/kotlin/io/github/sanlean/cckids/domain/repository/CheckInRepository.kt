package io.github.sanlean.cckids.domain.repository

import io.github.sanlean.cckids.domain.model.CheckIn
import io.github.sanlean.cckids.domain.result.Result

interface CheckInRepository {
    suspend fun listByAula(aulaId: String): Result<List<CheckIn>>
    suspend fun performCheckIn(aulaId: String, alunoId: String, professorId: String, dateTimeIso: String? = null): Result<CheckIn>
    suspend fun deleteCheckIn(id: String): Result<Unit>
}
