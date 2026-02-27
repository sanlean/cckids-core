package io.github.sanlean.cckids.data.mapper

import io.github.sanlean.cckids.data.dto.AulaDto
import io.github.sanlean.cckids.data.dto.AulaResumoDto
import io.github.sanlean.cckids.domain.model.Aula
import io.github.sanlean.cckids.domain.model.AulaResumo
import kotlinx.datetime.LocalDateTime

fun AulaDto.toDomain() = Aula(
    id = id,
    dateTime = LocalDateTime.parse(dateTime),
    culto = culto,
    tipo = tipo,
    status = status,
    turmas = turmas.map { it.toDomain() },
    professores = professores.map { it.toDomain() },
    materiais = materiais.map { it.toDomain() },
    alunos = alunos.map { it.toDomain() }
)

fun AulaResumoDto.toDomain() = AulaResumo(
    id = id,
    dateTime = LocalDateTime.parse(dateTime),
    culto = culto,
    tipo = tipo,
    status = status,
    turmasCount = turmasCount,
    professoresCount = professoresCount,
    materiaisCount = materiaisCount,
    alunosCount = alunosCount
)
