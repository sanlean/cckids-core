package io.github.sanlean.cckids.data.repository

import io.github.sanlean.cckids.data.dto.ProfessorDto
import io.github.sanlean.cckids.data.mapper.toDomain
import io.github.sanlean.cckids.data.request.executeRequest
import io.github.sanlean.cckids.domain.model.Professor
import io.github.sanlean.cckids.domain.repository.ProfessorRepository
import io.github.sanlean.cckids.domain.result.Result
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.CoroutineDispatcher

class ProfessorRepositoryImpl(
    private val client: HttpClient,
    private val dispatcher: CoroutineDispatcher
) : ProfessorRepository {
    override suspend fun listProfessores(): Result<List<Professor>> = executeRequest(dispatcher) {
        val list: List<ProfessorDto> = client.get(ApiRoutes.PROFESSORES).body()
        list.map { it.toDomain() }
    }

    override suspend fun getProfessor(id: String): Result<Professor> = executeRequest(dispatcher) {
        val dto: ProfessorDto = client.get(ApiRoutes.professor(id)).body()
        dto.toDomain()
    }
}
