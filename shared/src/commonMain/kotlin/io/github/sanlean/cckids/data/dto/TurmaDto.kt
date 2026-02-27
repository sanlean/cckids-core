package io.github.sanlean.cckids.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TurmaDto(
    @SerialName("id") val id: String,
    @SerialName("nome") val nome: String,
    @SerialName("faixa_etaria_min") val faixaEtariaMin: Int,
    @SerialName("faixa_etaria_max") val faixaEtariaMax: Int,
    @SerialName("ativa") val ativa: Boolean
)
