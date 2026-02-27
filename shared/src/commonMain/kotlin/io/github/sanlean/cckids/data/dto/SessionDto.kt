package io.github.sanlean.cckids.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SessionDto(
    @SerialName("token") val token: String,
    @SerialName("professor") val professor: ProfessorDto
)
