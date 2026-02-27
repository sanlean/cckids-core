package io.github.sanlean.cckids.data.mapper

import io.github.sanlean.cckids.data.dto.CheckInDto
import io.github.sanlean.cckids.domain.model.CheckIn
import kotlinx.datetime.LocalDateTime

fun CheckInDto.toDomain() = CheckIn(
    id = id,
    aulaId = aulaId,
    alunoId = alunoId,
    registradoPorProfessorId = registradoPorProfessorId,
    dataHora = LocalDateTime.parse(dataHora)
)
