package com.comseung.matrixexpert.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.comseung.matrixexpert.data.data_source.MatrixDatabase
import com.comseung.matrixlib.Matrix

@Entity(
    tableName = MatrixDatabase.MATRIX_T_NAME
)
data class MatrixDto (
    val rows : Int = 1,
    val cols : Int = 1,
    @PrimaryKey val name : String = "",
    val data : String = ""

)

fun Matrix.toMatrixDto() : MatrixDto {
    val str = this.dataString()
    return MatrixDto(
        rows = rows,
        cols = cols,
        name = name,
        data = str
    )
}

fun MatrixDto.toMatrix() : Matrix {
    val len = data.length
    val parsedData = this.data.subSequence(1, len-1)
        .split(",").map {
            it.trim().toDouble()
        }
    val ret = Matrix(this.rows, this.cols, this.name)
    for(r in 0 until rows)
        for(c in 0 until cols)
            ret[r][c] = parsedData[r*cols + c]
    return ret
}