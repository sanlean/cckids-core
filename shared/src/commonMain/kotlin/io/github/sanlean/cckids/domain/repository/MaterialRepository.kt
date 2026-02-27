package io.github.sanlean.cckids.domain.repository

import io.github.sanlean.cckids.domain.model.Material
import io.github.sanlean.cckids.domain.model.MaterialTipo
import io.github.sanlean.cckids.domain.result.Result

interface MaterialRepository {
    suspend fun listByAula(aulaId: String): Result<List<Material>>
    suspend fun uploadMaterial(aulaId: String, professorId: String, tipo: MaterialTipo, url: String): Result<Material>
    suspend fun downloadMaterial(id: String): Result<String>
}
