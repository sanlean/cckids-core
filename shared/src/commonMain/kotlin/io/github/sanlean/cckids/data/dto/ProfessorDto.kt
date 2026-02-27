package io.github.sanlean.cckids.data.dto

import io.github.sanlean.cckids.domain.model.ProfessorRole
import io.github.sanlean.cckids.domain.model.ProfessorStatus
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfessorDto(
    @SerialName("id") val id: String,
    @SerialName("nome") val nome: String,
    @SerialName("email") val email: String,
    @SerialName("status") val status: ProfessorStatus,
    @SerialName("role") val role: ProfessorRole
)
