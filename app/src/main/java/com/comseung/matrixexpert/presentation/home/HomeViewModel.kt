package com.comseung.matrixexpert.presentation.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comseung.matrixexpert.domain.use_case.GetMatrixFunctions
import com.comseung.matrixexpert.domain.use_case.GetMatrixList
import com.comseung.matrixexpert.domain.model.ExpressionParser
import com.comseung.matrixlib.Matrix
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMatrixList: GetMatrixList,
) : ViewModel() {

    private val _matList = MutableStateFlow(emptyList<Matrix>())
    val matList = _matList.asStateFlow()

    val matFuncList = MutableStateFlow(GetMatrixFunctions()).asStateFlow()

    init {
        viewModelScope.launch {
            getMatrixList().run {
                if(this.isSuccess){
                    _matList.value = this.getOrThrow()
                }

            }
        }
    }

    private val _consoleMessages = MutableStateFlow(listOf<String>(
    ))
    val consoleMessages = _consoleMessages.asStateFlow()

    val consoleMsg = mutableStateOf("")

    private fun addMsg(msg : String) {
        _consoleMessages.value = _consoleMessages.value.plus(msg)
    }

    fun onCalculate() {
        val res = ExpressionParser.calc(consoleMsg.value)
        addMsg(res)
    }
}