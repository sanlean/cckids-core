package io.github.sanlean.cckids.data.repository

import io.github.sanlean.cckids.data.dto.AlunoDto
import io.github.sanlean.cckids.data.dto.CreateAlunoRequestDto
import io.github.sanlean.cckids.data.mapper.toDomain
import io.github.sanlean.cckids.data.request.executeRequest
import io.github.sanlean.cckids.domain.model.Aluno
import io.github.sanlean.cckids.domain.model.Genero
import io.github.sanlean.cckids.domain.repository.AlunoRepository
import io.github.sanlean.cckids.domain.result.Result
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.coroutines.CoroutineDispatcher

class AlunoRepositoryImpl(
    private val client: HttpClient,
    private val dispatcher: CoroutineDispatcher
) : AlunoRepository {
    override suspend fun searchAlunos(
        nome: String?,
        genero: Genero?,
        ativo: Boolean?,
        turmaId: String?
    ): Result<List<Aluno>> = executeRequest(dispatcher) {
        val list: List<AlunoDto> = client.get(ApiRoutes.ALUNOS) {
            if (nome != null) parameter("nome", nome)
            if (genero != null) parameter("genero", genero.name)
            if (ativo != null) parameter("ativo", ativo)
            if (turmaId != null) parameter("turmaId", turmaId)
        }.body()
        list.map { it.toDomain() }
    }

    override suspend fun createAluno(
        nome: String,
        genero: Genero,
        dataNascimentoIso: String?,
        idadeManual: Int?,
        foto: String?,
        infoResponsavel: String?
    ): Result<Aluno> = executeRequest(dispatcher) {
        val dto: AlunoDto = client.post(ApiRoutes.ALUNOS) {
            setBody(
                CreateAlunoRequestDto(
                    nome = nome,
                    dataNascimento = dataNascimentoIso,
                    idadeManual = idadeManual,
                    genero = genero,
                    foto = foto,
                    infoResponsavel = infoResponsavel
                )
            )
        }.body()
        dto.toDomain()
    }

    override suspend fun getAluno(id: String): Result<Aluno> = executeRequest(dispatcher) {
        val dto: AlunoDto = client.get(ApiRoutes.aluno(id)).body()
        dto.toDomain()
    }
}
