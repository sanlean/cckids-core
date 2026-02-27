package io.github.sanlean.cckids.data.mapper

import io.github.sanlean.cckids.data.dto.MaterialDto
import io.github.sanlean.cckids.domain.model.Material
import kotlinx.datetime.LocalDateTime

fun MaterialDto.toDomain() = Material(
    id = id,
    aulaId = aulaId,
    enviadoPorProfessorId = enviadoPorProfessorId,
    tipo = tipo,
    dataHora = LocalDateTime.parse(dataHora),
    url = url
)
