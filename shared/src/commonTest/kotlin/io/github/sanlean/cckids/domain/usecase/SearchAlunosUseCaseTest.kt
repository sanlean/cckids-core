package io.github.sanlean.cckids.domain.usecase

import io.github.sanlean.cckids.domain.model.Aluno
import io.github.sanlean.cckids.domain.model.Genero
import io.github.sanlean.cckids.domain.repository.AlunoRepository
import io.github.sanlean.cckids.domain.result.Result
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SearchAlunosUseCaseTest {

    private class MockAlunoRepository : AlunoRepository {
        var searchAlunosCalled = false
        var lastNome: String? = null
        var lastGenero: Genero? = null
        var lastAtivo: Boolean? = null
        var lastTurmaId: String? = null

        override suspend fun searchAlunos(
            nome: String?,
            genero: Genero?,
            ativo: Boolean?,
            turmaId: String?
        ): Result<List<Aluno>> {
            searchAlunosCalled = true
            lastNome = nome
            lastGenero = genero
            lastAtivo = ativo
            lastTurmaId = turmaId
            return Result.Success(emptyList())
        }

        override suspend fun createAluno(
            nome: String,
            genero: Genero,
            dataNascimentoIso: String?,
            idadeManual: Int?,
            foto: String?,
            infoResponsavel: String?
        ): Result<Aluno> = Result.Failure(io.github.sanlean.cckids.domain.result.DomainError.Unknown())

        override suspend fun getAluno(id: String): Result<Aluno> =
             Result.Failure(io.github.sanlean.cckids.domain.result.DomainError.Unknown())
    }

    @Test
    fun `invoke should call repository with correct parameters`() = kotlinx.coroutines.test.runTest {
        val repository = MockAlunoRepository()
        val useCase = SearchAlunosUseCase(repository)

        val result = useCase(nome = "Test", genero = Genero.MASCULINO, ativo = true, turmaId = "t1")

        assertTrue(repository.searchAlunosCalled)
        assertEquals("Test", repository.lastNome)
        assertEquals(Genero.MASCULINO, repository.lastGenero)
        assertEquals(true, repository.lastAtivo)
        assertEquals("t1", repository.lastTurmaId)
        assertTrue(result is Result.Success)
    }
}
