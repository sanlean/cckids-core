package io.github.sanlean.cckids.domain.usecase

import io.github.sanlean.cckids.domain.model.Turma
import io.github.sanlean.cckids.domain.repository.AulaRepository
import io.github.sanlean.cckids.domain.result.Result

class ListAulaTurmasUseCase(private val repository: AulaRepository) {
    suspend operator fun invoke(aulaId: String): Result<List<Turma>> = repository.listTurmasDaAula(aulaId)
}
