package com.comseung.matrixexpert.presentation.add_matrix

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AddMatrixViewModel @Inject constructor(

) : ViewModel() {

    var matrixName = mutableStateOf("")
}