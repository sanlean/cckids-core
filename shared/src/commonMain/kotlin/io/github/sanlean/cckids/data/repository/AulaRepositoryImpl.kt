package io.github.sanlean.cckids.data.repository

import io.github.sanlean.cckids.data.dto.AulaDto
import io.github.sanlean.cckids.data.dto.AulaProfessorDto
import io.github.sanlean.cckids.data.dto.AulaResumoDto
import io.github.sanlean.cckids.data.dto.MaterialDto
import io.github.sanlean.cckids.data.dto.TurmaDto
import io.github.sanlean.cckids.data.mapper.toDomain
import io.github.sanlean.cckids.data.request.executeRequest
import io.github.sanlean.cckids.domain.model.Aula
import io.github.sanlean.cckids.domain.model.AulaProfessor
import io.github.sanlean.cckids.domain.model.AulaResumo
import io.github.sanlean.cckids.domain.model.Material
import io.github.sanlean.cckids.domain.model.Turma
import io.github.sanlean.cckids.domain.repository.AulaRepository
import io.github.sanlean.cckids.domain.result.Result
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.CoroutineDispatcher

class AulaRepositoryImpl(
    private val client: HttpClient,
    private val dispatcher: CoroutineDispatcher
) : AulaRepository {
    override suspend fun listAulas(fromIso: String?, toIso: String?): Result<List<AulaResumo>> =
        executeRequest(dispatcher) {
            val response: List<AulaResumoDto> = client.get(ApiRoutes.AULAS) {
                if (fromIso != null) parameter("from", fromIso)
                if (toIso != null) parameter("to", toIso)
            }.body()
            response.map { it.toDomain() }
        }

    override suspend fun getAula(id: String): Result<Aula> = executeRequest(dispatcher) {
        val dto: AulaDto = client.get(ApiRoutes.aula(id)).body()
        dto.toDomain()
    }

    override suspend fun listTurmasDaAula(aulaId: String): Result<List<Turma>> =
        executeRequest(dispatcher) {
            val list: List<TurmaDto> = client.get(ApiRoutes.aulaTurmas(aulaId)).body()
            list.map { it.toDomain() }
        }

    override suspend fun listProfessoresDaAula(aulaId: String): Result<List<AulaProfessor>> =
        executeRequest(dispatcher) {
            val list: List<AulaProfessorDto> = client.get(ApiRoutes.aulaProfessores(aulaId)).body()
            list.map { it.toDomain() }
        }

    override suspend fun listMateriaisDaAula(aulaId: String): Result<List<Material>> =
        executeRequest(dispatcher) {
            val list: List<MaterialDto> = client.get(ApiRoutes.aulaMateriais(aulaId)).body()
            list.map { it.toDomain() }
        }
}
