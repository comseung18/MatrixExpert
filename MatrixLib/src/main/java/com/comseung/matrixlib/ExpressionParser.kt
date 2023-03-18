package com.comseung.matrixlib

interface ExpressionParser {
    fun calc(expr: String) : String


    companion object : ExpressionParser {
        override fun calc(expr: String): String {
            return ""
        }
    }
}