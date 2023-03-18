package com.comseung.matrixexpert.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTextInputWithLabel(
    label: String,
    content: MutableState<String>,
    modifier: Modifier = Modifier,
    isNumberKeyboard: Boolean = false,
    labelFontSize: TextUnit = 20.sp,
    onValueChanged: (String)->Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()

    ) {
        Text(
            text = label,
            fontSize = labelFontSize,
            modifier = Modifier.padding(end = 15.dp)
        )

        TextField(
            value = content.value,
            onValueChange = {
                onValueChanged(it)
            },
            singleLine = true,
            modifier = modifier
                .fillMaxWidth(),
            keyboardOptions =
            if (isNumberKeyboard)
                KeyboardOptions(keyboardType = KeyboardType.Number)
            else KeyboardOptions.Default
        )
    }
}
