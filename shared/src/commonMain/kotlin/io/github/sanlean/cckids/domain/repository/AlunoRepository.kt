package io.github.sanlean.cckids.domain.repository

import io.github.sanlean.cckids.domain.model.Aluno
import io.github.sanlean.cckids.domain.model.Genero
import io.github.sanlean.cckids.domain.result.Result

interface AlunoRepository {
    suspend fun searchAlunos(
        nome: String? = null,
        genero: Genero? = null,
        ativo: Boolean? = null,
        turmaId: String? = null
    ): Result<List<Aluno>>

    suspend fun createAluno(
        nome: String,
        genero: Genero,
        dataNascimentoIso: String? = null,
        idadeManual: Int? = null,
        foto: String? = null,
        infoResponsavel: String? = null
    ): Result<Aluno>
    suspend fun getAluno(id: String): Result<Aluno>
}
