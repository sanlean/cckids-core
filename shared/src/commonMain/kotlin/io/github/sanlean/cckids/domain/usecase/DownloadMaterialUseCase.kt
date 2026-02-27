package io.github.sanlean.cckids.domain.usecase

import io.github.sanlean.cckids.domain.repository.MaterialRepository
import io.github.sanlean.cckids.domain.result.Result

class DownloadMaterialUseCase(private val repository: MaterialRepository) {
    suspend operator fun invoke(id: String): Result<String> = repository.downloadMaterial(id)
}
