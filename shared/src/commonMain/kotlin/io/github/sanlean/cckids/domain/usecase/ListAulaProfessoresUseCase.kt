package io.github.sanlean.cckids.domain.usecase

import io.github.sanlean.cckids.domain.model.AulaProfessor
import io.github.sanlean.cckids.domain.repository.AulaRepository
import io.github.sanlean.cckids.domain.result.Result

class ListAulaProfessoresUseCase(private val repository: AulaRepository) {
    suspend operator fun invoke(aulaId: String): Result<List<AulaProfessor>> = repository.listProfessoresDaAula(aulaId)
}
