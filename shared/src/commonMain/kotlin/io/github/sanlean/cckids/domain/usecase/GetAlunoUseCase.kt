package io.github.sanlean.cckids.domain.usecase

import io.github.sanlean.cckids.domain.model.Aluno
import io.github.sanlean.cckids.domain.repository.AlunoRepository
import io.github.sanlean.cckids.domain.result.Result

class GetAlunoUseCase(private val repository: AlunoRepository) {
    suspend operator fun invoke(id: String): Result<Aluno> = repository.getAluno(id)
}
