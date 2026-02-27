package io.github.sanlean.cckids.data.mapper

import io.github.sanlean.cckids.data.dto.AlunoDto
import io.github.sanlean.cckids.domain.model.Genero
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class AlunoMapperTest {

    @Test
    fun `toDomain should map AlunoDto to Aluno correctly`() {
        val dto = AlunoDto(
            id = "1",
            nome = "Joãozinho",
            dataNascimento = "2015-01-01",
            idadeManual = 9,
            genero = Genero.MASCULINO,
            foto = "http://foto.com",
            infoResponsavel = "Pai: José",
            ativo = true
        )

        val domain = dto.toDomain()

        assertEquals(dto.id, domain.id)
        assertEquals(dto.nome, domain.nome)
        assertEquals(dto.dataNascimento, domain.dataNascimento)
        assertEquals(dto.idadeManual, domain.idadeManual)
        assertEquals(dto.genero, domain.genero)
        assertEquals(dto.foto, domain.foto)
        assertEquals(dto.infoResponsavel, domain.infoResponsavel)
        assertEquals(dto.ativo, domain.ativo)
    }

    @Test
    fun `toDomain should handle null optional fields`() {
        val dto = AlunoDto(
            id = "2",
            nome = "Mariazinha",
            genero = Genero.FEMININO,
            ativo = false
        )

        val domain = dto.toDomain()

        assertEquals("2", domain.id)
        assertEquals(Genero.FEMININO, domain.genero)
        assertEquals(null, domain.dataNascimento)
        assertEquals(null, domain.idadeManual)
        assertEquals(null, domain.foto)
        assertEquals(null, domain.infoResponsavel)
        assertEquals(false, domain.ativo)
    }
}
