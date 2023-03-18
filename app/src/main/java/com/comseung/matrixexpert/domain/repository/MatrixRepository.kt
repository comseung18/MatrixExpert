package com.comseung.matrixexpert.domain.repository

import com.comseung.matrixexpert.data.MatrixDto
import com.comseung.matrixlib.Matrix

interface MatrixRepository {

    suspend fun getMatrices(): List<Matrix>

    suspend fun insertOrReplace(matrix: Matrix)
}