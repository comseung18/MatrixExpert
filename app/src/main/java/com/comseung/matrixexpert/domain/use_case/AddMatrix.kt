package com.comseung.matrixexpert.domain.use_case

import com.comseung.matrixexpert.domain.repository.MatrixRepository
import com.comseung.matrixlib.Matrix

class AddMatrix(
    private val matrixRepository: MatrixRepository
) {
    suspend operator fun invoke(matrix: Matrix) : Result<Unit>{
        return try {
            matrixRepository.insertOrReplace(matrix)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}