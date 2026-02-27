package io.github.sanlean.cckids.data.dto

import io.github.sanlean.cckids.domain.model.AulaStatus
import io.github.sanlean.cckids.domain.model.AulaTipo
import io.github.sanlean.cckids.domain.model.Culto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AulaDto(
    @SerialName("id") val id: String,
    @SerialName("data_hora") val dateTime: String,
    @SerialName("culto") val culto: Culto,
    @SerialName("tipo") val tipo: AulaTipo,
    @SerialName("status") val status: AulaStatus,
    @SerialName("turmas") val turmas: List<TurmaDto> = emptyList(),
    @SerialName("professores") val professores: List<AulaProfessorDto> = emptyList(),
    @SerialName("materiais") val materiais: List<MaterialDto> = emptyList(),
    @SerialName("alunos") val alunos: List<AlunoDto> = emptyList()
)

@Serializable
data class AulaResumoDto(
    @SerialName("id") val id: String,
    @SerialName("data_hora") val dateTime: String,
    @SerialName("culto") val culto: Culto,
    @SerialName("tipo") val tipo: AulaTipo,
    @SerialName("status") val status: AulaStatus,
    @SerialName("qtd_turmas") val turmasCount: Int,
    @SerialName("qtd_professores") val professoresCount: Int,
    @SerialName("qtd_materiais") val materiaisCount: Int,
    @SerialName("qtd_alunos") val alunosCount: Int
)
