package com.comseung.matrixexpert.presentation.add_matrix

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comseung.matrixexpert.domain.use_case.AddMatrix
import com.comseung.matrixexpert.domain.use_case.GetMatrixDefaultName
import com.comseung.matrixlib.Matrix
import com.comseung.matrixlib.setCols
import com.comseung.matrixlib.setRows
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddMatrixViewModel @Inject constructor(
    private val getMatrixDefaultName: GetMatrixDefaultName,
    private val addMatrixUseCase: AddMatrix
) : ViewModel() {

    private var mat = Matrix()

    val matrixName = mutableStateOf("")
    val matrixRow = mutableStateOf("1")
    val matrixCol = mutableStateOf("1")

    init {
        viewModelScope.launch {
            matrixName.value = getMatrixDefaultName()
        }
    }
    fun onRowChanged(newRow: String) {
        val row = newRow.toIntDefault(mat.rows)
        mat = mat.setRows(row)
        matrixRow.value = newRow
    }

    fun onColChanged(newCol: String) {
        val col = newCol.toIntDefault(mat.cols)
        mat = mat.setCols(col)
        matrixCol.value = newCol
    }

    fun getCellData(row: Int, col: Int) = 0.0
    fun getRows() = mat.rows
    fun getCols() = mat.cols

    fun printInfo() = println(mat)


    fun onCellChanged(row: Int, col: Int, v : String) {
        mat[row][col] = v.toDoubleDefault(mat[row][col])
    }

    fun addMatrix() {
        CoroutineScope(Dispatchers.Default).launch {
            mat = mat.copy(name = matrixName.value)
            addMatrixUseCase(mat)
        }
    }

}

fun String.toIntDefault(default: Int) : Int = try {
    this.toInt()
} catch (e: NumberFormatException) {
    default
}

fun String.toDoubleDefault(default: Double) : Double = try {
    this.toDouble()
} catch (e: NumberFormatException) {
    default
}