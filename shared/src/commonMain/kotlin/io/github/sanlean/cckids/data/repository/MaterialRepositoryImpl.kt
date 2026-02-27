package io.github.sanlean.cckids.data.repository

import io.github.sanlean.cckids.data.dto.MaterialDto
import io.github.sanlean.cckids.data.dto.MaterialUploadRequestDto
import io.github.sanlean.cckids.data.mapper.toDomain
import io.github.sanlean.cckids.data.request.executeRequest
import io.github.sanlean.cckids.domain.model.Material
import io.github.sanlean.cckids.domain.model.MaterialTipo
import io.github.sanlean.cckids.domain.repository.MaterialRepository
import io.github.sanlean.cckids.domain.result.Result
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.coroutines.CoroutineDispatcher

class MaterialRepositoryImpl(
    private val client: HttpClient,
    private val dispatcher: CoroutineDispatcher
) : MaterialRepository {
    override suspend fun listByAula(aulaId: String): Result<List<Material>> = executeRequest(dispatcher) {
        val list: List<MaterialDto> = client.get(ApiRoutes.aulaMateriais(aulaId)).body()
        list.map { it.toDomain() }
    }

    override suspend fun uploadMaterial(
        aulaId: String,
        professorId: String,
        tipo: MaterialTipo,
        url: String
    ): Result<Material> = executeRequest(dispatcher) {
        val dto: MaterialDto = client.post(ApiRoutes.MATERIAIS) {
            setBody(MaterialUploadRequestDto(aulaId, professorId, tipo, url))
        }.body()
        dto.toDomain()
    }

    override suspend fun downloadMaterial(id: String): Result<String> = executeRequest(dispatcher) {
        client.get(ApiRoutes.materialDownload(id)).body()
    }
}
