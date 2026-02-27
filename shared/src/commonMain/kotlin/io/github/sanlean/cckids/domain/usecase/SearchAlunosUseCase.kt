package io.github.sanlean.cckids.domain.usecase

import io.github.sanlean.cckids.domain.model.Aluno
import io.github.sanlean.cckids.domain.model.Genero
import io.github.sanlean.cckids.domain.repository.AlunoRepository
import io.github.sanlean.cckids.domain.result.Result

class SearchAlunosUseCase(private val repository: AlunoRepository) {
    suspend operator fun invoke(
        nome: String? = null,
        genero: Genero? = null,
        ativo: Boolean? = null,
        turmaId: String? = null
    ): Result<List<Aluno>> = repository.searchAlunos(nome, genero, ativo, turmaId)
}
