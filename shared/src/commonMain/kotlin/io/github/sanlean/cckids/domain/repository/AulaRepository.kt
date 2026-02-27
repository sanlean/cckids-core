package io.github.sanlean.cckids.domain.repository

import io.github.sanlean.cckids.domain.model.*
import io.github.sanlean.cckids.domain.result.Result

interface AulaRepository {
    suspend fun listAulas(fromIso: String? = null, toIso: String? = null): Result<List<AulaResumo>>
    suspend fun getAula(id: String): Result<Aula>
    suspend fun listTurmasDaAula(aulaId: String): Result<List<Turma>>
    suspend fun listProfessoresDaAula(aulaId: String): Result<List<AulaProfessor>>
    suspend fun listMateriaisDaAula(aulaId: String): Result<List<Material>>
}
