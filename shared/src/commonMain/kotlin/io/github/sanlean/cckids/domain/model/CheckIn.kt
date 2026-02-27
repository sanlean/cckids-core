package io.github.sanlean.cckids.domain.model

import kotlinx.datetime.LocalDateTime

data class CheckIn(
    val id: String,
    val aulaId: String,
    val alunoId: String,
    val registradoPorProfessorId: String,
    val dataHora: LocalDateTime
)
