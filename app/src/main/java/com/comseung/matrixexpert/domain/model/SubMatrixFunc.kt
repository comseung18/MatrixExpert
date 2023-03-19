package com.comseung.matrixexpert.domain.model

import com.comseung.matrixlib.Matrix
import com.comseung.matrixlib.minus

object SubMatrixFunc : MatrixFunc.Binary<Matrix, Matrix, Matrix> {
    override fun calc(s: Matrix, t: Matrix): Matrix {
        return s - t
    }

    override val name: String
        get() = "Sub"
}