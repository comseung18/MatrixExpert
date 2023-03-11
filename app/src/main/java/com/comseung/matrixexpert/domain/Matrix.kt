package com.comseung.matrixexpert.domain

class Matrix(
    val rows : Int = 1,
    val cols : Int = 1
) {

    val data : Array<DoubleArray>
    init {
        if(rows <= 0 ||
            cols <= 0) throw IllegalArgumentException()

        data = Array(rows) { DoubleArray(cols) }
    }
}

fun zeros(rows: Int, cols: Int) = Matrix(rows, cols)
fun zeros(n : Int) = zeros(n, n)

fun ones(rows: Int, cols: Int) : Matrix {
    val ret = Matrix(rows, cols)
    for(r in 0 until rows)
        for(c in 0 until cols)
            ret.data[r][c] = 1.0
    return ret
}
fun ones(n: Int) = ones(n, n)


