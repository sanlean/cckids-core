package io.github.sanlean.cckids.data.dto

import io.github.sanlean.cckids.domain.model.MaterialTipo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MaterialDto(
    @SerialName("id") val id: String,
    @SerialName("aula_id") val aulaId: String,
    @SerialName("professor_id") val enviadoPorProfessorId: String,
    @SerialName("tipo") val tipo: MaterialTipo,
    @SerialName("data_hora") val dataHora: String,
    @SerialName("url") val url: String
)
