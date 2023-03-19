package com.comseung.matrixexpert.domain.use_case

import com.comseung.matrixexpert.domain.model.AddMatrixFunc
import com.comseung.matrixexpert.domain.model.MatrixFunc
import com.comseung.matrixexpert.domain.model.SubMatrixFunc

object GetMatrixFunctions {

    operator fun invoke() : List<MatrixFunc<*>> {
        return listOf(
            AddMatrixFunc,
            SubMatrixFunc
        )
    }
}