package com.comseung.matrixexpert.presentation.add_matrix

import android.graphics.BlendModeColorFilter
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.comseung.matrixexpert.presentation.EditTextInputWithLabel
import com.comseung.matrixexpert.presentation.home.HomeViewModel

@Composable
fun AddMatrixScreen(
    viewModel: AddMatrixViewModel,
    modifier: Modifier = Modifier,
    onNavigationToHome: ()->Unit = {},

) {

    val matrixName = remember { viewModel.matrixName }
    val matrixCol = remember { viewModel.matrixCol }
    val matrixRow = remember { viewModel.matrixRow }

    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(15.dp)
            .verticalScroll(scrollState)
    ) {
        AddMatrixScreenHeader(
            matrixName,
            matrixRow,
            { viewModel.onRowChanged(it) },
            matrixCol,
            { viewModel.onColChanged(it) },
        )

        Spacer(modifier = Modifier.height(20.dp))

        MatrixCells(
            rows = matrixRow.value.toIntDefault(viewModel.getRows()),
            cols = matrixCol.value.toIntDefault(viewModel.getCols()),
            onCellChanged = viewModel::onCellChanged,
            getCellData = viewModel::getCellData,
            modifier = Modifier.fillMaxSize()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = {
                    viewModel.addMatrix()
                    onNavigationToHome()
                },
            ) {
                Text(text = "Add")
            }
        }

    }

}

@Composable
private fun AddMatrixScreenHeader(
    matrixName: MutableState<String>,
    matrixRow: MutableState<String>,
    matrixRowChanged: (String) -> Unit,
    matrixCol: MutableState<String>,
    matrixColChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        EditTextInputWithLabel(
            label = "name",
            content = matrixName,
            onValueChanged = {
                matrixName.value = it
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row {
            EditTextInputWithLabel(
                label = "rows",
                content = matrixRow,
                modifier = Modifier.weight(1f),
                isNumberKeyboard = true,
                onValueChanged = matrixRowChanged
            )

            Spacer(modifier = Modifier.width(5.dp))

            EditTextInputWithLabel(
                label = "cols",
                content = matrixCol,
                modifier = Modifier.weight(1f),
                isNumberKeyboard = true,
                onValueChanged = matrixColChanged
            )
        }
    }
}

@Composable
fun MatrixCells(
    rows: Int,
    cols: Int,
    onCellChanged: (Int, Int, String) -> Unit,
    getCellData: (Int, Int) -> Double,
    modifier: Modifier = Modifier
) {


    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        for (r in 0 until rows) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)

            ) {
                for (c in 0 until cols) {
                    EditTextMatrixCell(
                        cellValue = getCellData(r, c),
                        onValueChanged = { onCellChanged(r, c, it) },
                        modifier = Modifier
                            .weight(1f)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTextMatrixCell(
    cellValue: Double,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 20.sp,
    onValueChanged: (String) -> Unit
) {
    val v = remember {
        mutableStateOf("0.0")
    }

    TextField(
        value = v.value,
        onValueChange = {
            v.value = it
            onValueChanged(it)
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = modifier,
        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center)
    )
}
