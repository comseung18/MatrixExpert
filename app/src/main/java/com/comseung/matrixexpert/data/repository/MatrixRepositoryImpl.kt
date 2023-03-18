package com.comseung.matrixexpert.data.repository

import com.comseung.matrixexpert.data.data_source.MatrixDao
import com.comseung.matrixexpert.data.toMatrix
import com.comseung.matrixexpert.data.toMatrixDto
import com.comseung.matrixexpert.domain.repository.MatrixRepository
import com.comseung.matrixlib.Matrix

class MatrixRepositoryImpl(
    private val matrixDao: MatrixDao
) : MatrixRepository {
    override suspend fun getMatrices(): List<Matrix> {
        return matrixDao.getMatrices().map { it.toMatrix() }
    }

    override suspend fun insertOrReplace(matrix: Matrix) {
        matrixDao.insertOrReplace(matrix.toMatrixDto())
    }
}