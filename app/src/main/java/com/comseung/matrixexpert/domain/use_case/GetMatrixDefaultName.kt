package com.comseung.matrixexpert.domain.use_case

import com.comseung.matrixexpert.domain.repository.MatrixRepository

class GetMatrixDefaultName(
    private val matrixRepository: MatrixRepository
) {

    suspend operator fun invoke() : String {
        val names = matrixRepository.getMatrices().map{it.name}
        for(c in 'a' ..'z') {
            if(names.contains(c.toString()).not()){
                return c.toString()
            }
        }

        for(n in 1..1000){
            for(c in 'a' ..'z') {
                if(names.contains(c.toString() + n.toString()).not()){
                    return c.toString() + n.toString()
                }
            }
        }

        throw Exception("matrix name full")
    }
}