package io.github.sanlean.cckids.data.mapper

import io.github.sanlean.cckids.data.dto.ProfessorDto
import io.github.sanlean.cckids.domain.model.Professor

fun ProfessorDto.toDomain() = Professor(id, nome, email, status, role)
