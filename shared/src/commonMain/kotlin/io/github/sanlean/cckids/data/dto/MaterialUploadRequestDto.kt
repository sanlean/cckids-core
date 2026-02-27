package io.github.sanlean.cckids.data.dto

import io.github.sanlean.cckids.domain.model.MaterialTipo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MaterialUploadRequestDto(
    @SerialName("aula_id") val aulaId: String,
    @SerialName("professor_id") val enviadoPorProfessorId: String,
    @SerialName("tipo") val tipo: MaterialTipo,
    @SerialName("url") val url: String
)
