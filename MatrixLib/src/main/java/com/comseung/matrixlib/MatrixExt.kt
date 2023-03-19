package com.comseung.matrixlib

import kotlin.math.min

fun zeros(rows: Int, cols: Int) = Matrix(rows, cols)
fun zeros(n : Int) = zeros(n, n)

fun ones(rows: Int, cols: Int) : Matrix {
    val ret = Matrix(rows, cols)
    for(r in 0 until rows)
        ret[r] = DoubleArray(cols) { 1.0 }
    return ret
}
fun ones(n: Int) = ones(n, n)

fun identify(rows: Int, cols: Int) : Matrix {
    val ret = Matrix(rows, cols)
    for(i in 0 until rows)
        ret[i][i] = 1.0

    return ret
}
fun identify(n: Int) = identify(n, n)

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

operator fun Matrix.plus(matrix: Matrix): Matrix {
    if(matrix.rows != rows || matrix.cols != cols) throw IllegalArgumentException()
    val ret = Matrix(matrix.rows, matrix.cols)
    for(r in 0 until rows)
        for(c in 0 until cols)
            ret[r][c] = matrix[r][c] + this[r][c]
    return ret
}

operator fun Matrix.minus(matrix: Matrix): Matrix {
    if(matrix.rows != rows || matrix.cols != cols) throw IllegalArgumentException()
    val ret = Matrix(matrix.rows, matrix.cols)
    for(r in 0 until rows)
        for(c in 0 until cols)
            ret[r][c] = this[r][c] - matrix[r][c]
    return ret
}

