package io.github.sanlean.cckids.domain.model

data class Professor(
    val id: String,
    val nome: String,
    val email: String,
    val status: ProfessorStatus,
    val role: ProfessorRole
)
