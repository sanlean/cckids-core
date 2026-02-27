package io.github.sanlean.cckids.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CheckInDto(
    @SerialName("id") val id: String,
    @SerialName("aula_id") val aulaId: String,
    @SerialName("aluno_id") val alunoId: String,
    @SerialName("professor_id") val registradoPorProfessorId: String,
    @SerialName("data_hora") val dataHora: String
)
