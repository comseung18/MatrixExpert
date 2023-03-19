package com.comseung.matrixexpert.domain.model

sealed interface MatrixFunc <R> {
    val name : String


    interface Unary<T, R> : MatrixFunc<R> {
        fun calc(t : T) : R
    }
    interface Binary<S, T, R> : MatrixFunc<R> {
        fun calc(s : S, t : T) : R
    }
}