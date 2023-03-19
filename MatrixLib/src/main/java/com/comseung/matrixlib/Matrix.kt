package com.comseung.matrixlib

import kotlin.math.round

data class Matrix(
    val rows : Int = 1,
    val cols : Int = 1,
    val name : String = "",
) {

    private val data : Array<DoubleArray>
    init {
        if(rows <= 0 ||
            cols <= 0) throw IllegalArgumentException()

        data = Array(rows) { DoubleArray(cols) }


    }

    operator fun get(r: Int) = data[r]
    operator fun get(r: Int, c: Int) = data[r][c]
    operator fun set(r: Int, value: DoubleArray) {
        data[r] = value
    }
    operator fun set(r: Int, c: Int, value: Double) {
        data[r][c] = value
    }

    fun rowExchange(r1 : Int, r2 : Int ): Unit {
        if(r1 !in 0 until rows ||
                r2 !in 0 until rows) throw IllegalArgumentException()

        if(r1 == r2) return

        val swap = data[r1].copyOf()
        data[r1] = data[r2].copyOf()
        data[r2] = swap
    }

    fun dataString(): String {
        return data.toList().flatMap {
            it.toList()
        }.toString()
    }



    override fun toString(): String {
        val buffer = StringBuilder()
        buffer.append("===== $name  =====\n")
        data.forEach { row ->
            row.joinTo(
                buffer,
                separator = " | ",
                postfix = "\n",
            ) {
                (round(it*10)/10).toString()
            }
        }
        buffer.append("==========\n")
        return buffer.toString()
    }
}

