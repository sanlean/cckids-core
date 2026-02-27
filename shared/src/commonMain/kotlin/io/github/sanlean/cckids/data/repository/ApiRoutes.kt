package io.github.sanlean.cckids.data.repository

internal object ApiRoutes {
    const val LOGIN = "/auth/login"

    const val AULAS = "/aulas"
    fun aula(id: String) = "/aulas/$id"
    fun aulaTurmas(id: String) = "/aulas/$id/turmas"
    fun aulaProfessores(id: String) = "/aulas/$id/professores"
    fun aulaMateriais(id: String) = "/aulas/$id/materiais"
    fun aulaCheckins(id: String) = "/aulas/$id/checkins"

    const val PROFESSORES = "/professores"
    fun professor(id: String) = "/professores/$id"

    const val ALUNOS = "/alunos"
    fun aluno(id: String) = "/alunos/$id"

    const val CHECKINS = "/checkins"
    fun checkin(id: String) = "/checkins/$id"

    const val MATERIAIS = "/materiais"
    fun material(id: String) = "/materiais/$id"
    fun materialDownload(id: String) = "/materiais/$id/download"
}
