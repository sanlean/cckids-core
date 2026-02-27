package io.github.sanlean.cckids.domain.model

import kotlinx.datetime.LocalDateTime

data class Aula(
    val id: String,
    val dateTime: LocalDateTime,
    val culto: Culto,
    val tipo: AulaTipo,
    val status: AulaStatus,
    val turmas: List<Turma> = emptyList(),
    val professores: List<AulaProfessor> = emptyList(),
    val materiais: List<Material> = emptyList(),
    val alunos: List<Aluno> = emptyList() // Adicionado conforme solicitado "alunos vinculados"
)

data class AulaResumo(
    val id: String,
    val dateTime: LocalDateTime,
    val culto: Culto,
    val tipo: AulaTipo,
    val status: AulaStatus,
    val turmasCount: Int,
    val professoresCount: Int,
    val materiaisCount: Int,
    val alunosCount: Int
)
