package io.github.sanlean.cckids.domain.usecase

import io.github.sanlean.cckids.domain.model.Aluno
import io.github.sanlean.cckids.domain.model.Genero
import io.github.sanlean.cckids.domain.repository.AlunoRepository
import io.github.sanlean.cckids.domain.result.Result

class CreateAlunoUseCase(private val repository: AlunoRepository) {
    suspend operator fun invoke(
        nome: String,
        genero: Genero,
        dataNascimentoIso: String? = null,
        idadeManual: Int? = null,
        foto: String? = null,
        infoResponsavel: String? = null
    ): Result<Aluno> = repository.createAluno(nome, genero, dataNascimentoIso, idadeManual, foto, infoResponsavel)
}
