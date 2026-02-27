package io.github.sanlean.cckids.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// Enums do domínio
@Serializable
enum class Culto { DOMINGO_MANHA, DOMINGO_NOITE, QUINTA }

@Serializable
enum class AulaTipo { NORMAL, EVENTO }

// Status é derivado conforme PRD
@Serializable
enum class AulaStatus { PLANNED, WARNING, DONE }

@Serializable
enum class ProfessorStatus { PENDING, APPROVED }

@Serializable
enum class ProfessorRole { COORDINATOR, PROFESSOR }

@Serializable
enum class ProfessorPapel { PROFESSOR, AUXILIAR }

@Serializable
enum class MaterialTipo { FOTO, PDF, OUTRO }

@Serializable
enum class Genero { MASCULINO, FEMININO, OUTRO }
