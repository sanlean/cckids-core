package io.github.sanlean.cckids.data.mapper

import io.github.sanlean.cckids.data.dto.TurmaDto
import io.github.sanlean.cckids.domain.model.Turma

fun TurmaDto.toDomain() = Turma(id, nome, faixaEtariaMin, faixaEtariaMax, ativa)
