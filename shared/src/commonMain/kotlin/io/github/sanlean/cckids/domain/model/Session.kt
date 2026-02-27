package io.github.sanlean.cckids.domain.model

// Sessão básica para autenticação

data class Session(
    val token: String,
    val professor: Professor
)
