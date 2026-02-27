package io.github.sanlean.cckids.data.mapper

import io.github.sanlean.cckids.data.dto.AulaDto
import io.github.sanlean.cckids.data.dto.AulaResumoDto
import io.github.sanlean.cckids.domain.model.AulaStatus
import io.github.sanlean.cckids.domain.model.AulaTipo
import io.github.sanlean.cckids.domain.model.Culto
import kotlinx.datetime.LocalDateTime
import kotlin.test.Test
import kotlin.test.assertEquals

class AulaMapperTest {

    @Test
    fun `AulaDto toDomain should map correctly`() {
        val dto = AulaDto(
            id = "a1",
            dateTime = "2024-03-10T09:00:00",
            culto = Culto.DOMINGO_MANHA,
            tipo = AulaTipo.NORMAL,
            status = AulaStatus.PLANNED,
            turmas = emptyList(),
            professores = emptyList(),
            materiais = emptyList(),
            alunos = emptyList()
        )

        val domain = dto.toDomain()

        assertEquals(dto.id, domain.id)
        assertEquals(LocalDateTime(2024, 3, 10, 9, 0, 0), domain.dateTime)
        assertEquals(dto.culto, domain.culto)
        assertEquals(dto.tipo, domain.tipo)
        assertEquals(dto.status, domain.status)
        assertEquals(0, domain.turmas.size)
    }

    @Test
    fun `AulaResumoDto toDomain should map correctly`() {
        val dto = AulaResumoDto(
            id = "r1",
            dateTime = "2024-03-10T18:00:00",
            culto = Culto.DOMINGO_NOITE,
            tipo = AulaTipo.EVENTO,
            status = AulaStatus.DONE,
            turmasCount = 2,
            professoresCount = 3,
            materiaisCount = 4,
            alunosCount = 10
        )

        val domain = dto.toDomain()

        assertEquals(dto.id, domain.id)
        assertEquals(LocalDateTime(2024, 3, 10, 18, 0, 0), domain.dateTime)
        assertEquals(dto.culto, domain.culto)
        assertEquals(dto.tipo, domain.tipo)
        assertEquals(dto.status, domain.status)
        assertEquals(2, domain.turmasCount)
        assertEquals(3, domain.professoresCount)
        assertEquals(4, domain.materiaisCount)
        assertEquals(10, domain.alunosCount)
    }
}
