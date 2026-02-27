package io.github.sanlean.cckids.data.repository

import io.github.sanlean.cckids.data.dto.CheckInDto
import io.github.sanlean.cckids.data.dto.CheckInRequestDto
import io.github.sanlean.cckids.data.mapper.toDomain
import io.github.sanlean.cckids.data.request.executeRequest
import io.github.sanlean.cckids.domain.model.CheckIn
import io.github.sanlean.cckids.domain.repository.CheckInRepository
import io.github.sanlean.cckids.domain.result.Result
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.coroutines.CoroutineDispatcher

class CheckInRepositoryImpl(
    private val client: HttpClient,
    private val dispatcher: CoroutineDispatcher
) : CheckInRepository {
    override suspend fun listByAula(aulaId: String): Result<List<CheckIn>> = executeRequest(dispatcher) {
        val list: List<CheckInDto> = client.get(ApiRoutes.aulaCheckins(aulaId)).body()
        list.map { it.toDomain() }
    }

    override suspend fun performCheckIn(
        aulaId: String,
        alunoId: String,
        professorId: String,
        dateTimeIso: String?
    ): Result<CheckIn> = executeRequest(dispatcher) {
        val dto: CheckInDto = client.post(ApiRoutes.CHECKINS) {
            setBody(CheckInRequestDto(aulaId, alunoId, professorId, dateTimeIso))
        }.body()
        dto.toDomain()
    }

    override suspend fun deleteCheckIn(id: String): Result<Unit> = executeRequest(dispatcher) {
        client.delete(ApiRoutes.checkin(id))
        Unit
    }
}
