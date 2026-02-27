package io.github.sanlean.cckids.domain.model

import kotlinx.datetime.LocalDateTime

data class Material(
    val id: String,
    val aulaId: String,
    val enviadoPorProfessorId: String,
    val tipo: MaterialTipo,
    val dataHora: LocalDateTime,
    val url: String
)
