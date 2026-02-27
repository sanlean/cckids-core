package io.github.sanlean.cckids.data.mapper

import io.github.sanlean.cckids.data.dto.SessionDto
import io.github.sanlean.cckids.domain.model.Session

fun SessionDto.toDomain() = Session(
    token = token,
    professor = professor.toDomain()
)
