package io.github.sanlean.cckids.domain.repository

import io.github.sanlean.cckids.domain.model.Professor
import io.github.sanlean.cckids.domain.result.Result

interface ProfessorRepository {
    suspend fun listProfessores(): Result<List<Professor>>
    suspend fun getProfessor(id: String): Result<Professor>
}
