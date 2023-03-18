package com.comseung.matrixexpert.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.comseung.matrixexpert.data.MatrixDto


@Database(entities = [MatrixDto::class], version = 1, exportSchema = false)
abstract class MatrixDatabase : RoomDatabase() {
    abstract val matrixDao : MatrixDao

    companion object {
        const val DB_NAME = "matrix_db"
        const val MATRIX_T_NAME = "matrix_dto"

    }
}