package io.github.sanlean.cckids.data.dto

import io.github.sanlean.cckids.domain.model.ProfessorPapel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AulaProfessorDto(
    @SerialName("professor") val professor: ProfessorDto,
    @SerialName("papel") val papel: ProfessorPapel
)
