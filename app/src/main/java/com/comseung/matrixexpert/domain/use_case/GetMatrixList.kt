package com.comseung.matrixexpert.domain.use_case

import com.comseung.matrixexpert.domain.repository.MatrixRepository
import com.comseung.matrixlib.Matrix

class GetMatrixList(
    private val matrixRepository: MatrixRepository
) {
    suspend operator fun invoke(): Result<List<Matrix>> {
        return try {
            Result.success(matrixRepository.getMatrices())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}