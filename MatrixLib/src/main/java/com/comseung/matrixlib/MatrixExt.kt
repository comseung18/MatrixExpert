package com.comseung.matrixlib

import kotlin.math.min

fun Matrix.setCols(newCols : Int) : Matrix {
    val ret = Matrix(rows, newCols)

    for(r in 0 until rows) {
        ret[r] = DoubleArray(newCols) {
            if(it < cols) this[r][it]
            else 0.0
        }
    }

    return ret
}

fun Matrix.setRows(newRows : Int) : Matrix {
    val ret = Matrix(newRows, cols)

    for(r in 0 until min(newRows, rows)) {
        ret[r] = this[r].copyOf()
    }

    return ret
}