package com.comseung.matrixexpert.di

import android.app.Application
import androidx.room.Room
import com.comseung.matrixexpert.data.data_source.MatrixDatabase
import com.comseung.matrixexpert.data.repository.MatrixRepositoryImpl
import com.comseung.matrixexpert.domain.repository.MatrixRepository
import com.comseung.matrixexpert.domain.use_case.GetMatrixDefaultName
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@dagger.Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    @Singleton
    fun provideMatrixDatabase(app: Application): MatrixDatabase {
        return Room.databaseBuilder(
            app,
            MatrixDatabase::class.java,
            MatrixDatabase.DB_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideMatrixRepository(matrixDatabase: MatrixDatabase): MatrixRepository {
        return MatrixRepositoryImpl(matrixDatabase.matrixDao)
    }

    @Provides
    @Singleton
    fun provideGetMatrixDefaultName(matrixRepository: MatrixRepository): GetMatrixDefaultName{
        return GetMatrixDefaultName(matrixRepository)
    }
}