package io.github.sanlean.cckids.domain.model

data class Aluno(
    val id: String,
    val nome: String,
    val dataNascimento: String?,
    val idadeManual: Int?,
    val genero: Genero,
    val foto: String?,
    val infoResponsavel: String?,
    val ativo: Boolean
)
