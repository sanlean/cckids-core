package io.github.sanlean.cckids.domain.model

data class Turma(
    val id: String,
    val nome: String,
    val faixaEtariaMin: Int,
    val faixaEtariaMax: Int,
    val ativa: Boolean
)
