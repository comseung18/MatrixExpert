package com.comseung.matrixexpert.presentation.add_matrix

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@Preview
@Composable
fun AddMatrixScreen(
    modifier: Modifier = Modifier
) {
    val viewModel: AddMatrixViewModel = viewModel()

    val matrixName = remember { viewModel.matrixName }

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        AddMatrixScreenHeader(matrixName)
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddMatrixScreenHeader(
    matrixName: MutableState<String>,
    modifier: Modifier = Modifier
) {

}