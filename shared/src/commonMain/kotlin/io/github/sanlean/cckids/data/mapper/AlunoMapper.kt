package io.github.sanlean.cckids.data.mapper

import io.github.sanlean.cckids.data.dto.AlunoDto
import io.github.sanlean.cckids.domain.model.Aluno

fun AlunoDto.toDomain() = Aluno(
    id = id,
    nome = nome,
    dataNascimento = dataNascimento,
    idadeManual = idadeManual,
    genero = genero,
    foto = foto,
    infoResponsavel = infoResponsavel,
    ativo = ativo
)
