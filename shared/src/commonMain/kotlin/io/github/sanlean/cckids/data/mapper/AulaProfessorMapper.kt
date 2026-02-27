package io.github.sanlean.cckids.data.mapper

import io.github.sanlean.cckids.data.dto.AulaProfessorDto
import io.github.sanlean.cckids.domain.model.AulaProfessor

fun AulaProfessorDto.toDomain() = AulaProfessor(
    professor = professor.toDomain(),
    papel = papel
)
