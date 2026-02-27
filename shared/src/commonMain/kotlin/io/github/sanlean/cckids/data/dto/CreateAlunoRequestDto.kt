package io.github.sanlean.cckids.data.dto

import io.github.sanlean.cckids.domain.model.Genero
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateAlunoRequestDto(
    @SerialName("nome") val nome: String,
    @SerialName("data_nascimento") val dataNascimento: String? = null,
    @SerialName("idade_manual") val idadeManual: Int? = null,
    @SerialName("genero") val genero: Genero,
    @SerialName("foto_url") val foto: String? = null,
    @SerialName("info_responsavel") val infoResponsavel: String? = null
)
