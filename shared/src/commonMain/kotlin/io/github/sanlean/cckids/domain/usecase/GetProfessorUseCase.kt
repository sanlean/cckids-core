package io.github.sanlean.cckids.domain.usecase

import io.github.sanlean.cckids.domain.model.Professor
import io.github.sanlean.cckids.domain.repository.ProfessorRepository
import io.github.sanlean.cckids.domain.result.Result

class GetProfessorUseCase(private val repository: ProfessorRepository) {
    suspend operator fun invoke(id: String): Result<Professor> = repository.getProfessor(id)
}
