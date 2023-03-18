package com.comseung.matrixexpert.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.comseung.matrixexpert.data.MatrixDto

@Dao
interface MatrixDao {

    @Query("SELECT * FROM ${MatrixDatabase.MATRIX_T_NAME}")
    suspend fun getMatrices(): List<MatrixDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(matrix: MatrixDto)


}