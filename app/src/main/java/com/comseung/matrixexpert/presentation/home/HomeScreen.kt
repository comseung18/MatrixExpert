package com.comseung.matrixexpert.presentation.home

import android.content.pm.ActivityInfo
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.comseung.matrixexpert.LockScreenOrientation
import com.comseung.matrixexpert.domain.model.MatrixFunc
import com.comseung.matrixlib.Matrix
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier,
    onNavigationToAddMatrix: ()->Unit = {},
) {
    LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)


    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        HorizontalMatrixList(
            matList = viewModel.matList.collectAsState().value
        )

        MatrixConsole(
            funcList = viewModel.matFuncList.collectAsState().value,
            modifier = Modifier
                .padding(15.dp),
            consoleMessages = viewModel.consoleMessages.collectAsState().value,
            msg = viewModel.consoleMsg,
            onSendMsg = viewModel::onCalculate
        )
        

    }
}

@Composable
fun HorizontalMatrixList(
    matList : List<Matrix>,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Column(modifier = modifier
        .fillMaxWidth()
        .padding(15.dp)
        .border(BorderStroke(1.dp, MaterialTheme.colorScheme.primary))

    ) {

        Text(
            text = "Matrices",
            fontSize = 23.sp,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.secondary),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSecondary
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = modifier
                .fillMaxWidth()
                .horizontalScroll(scrollState)
            ,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            matList.forEach{
                MatrixItem(matrix = it)
            }
        }
    }

}

@Preview
@Composable
fun MatrixItem(
    modifier: Modifier = Modifier,
    matrix: Matrix = Matrix(name="x"),
) {
    Column(
        modifier = modifier

            .padding(5.dp)
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .border(BorderStroke(1.dp, Color.Black))
            .padding(10.dp)
            ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = matrix.name, fontSize = 23.sp, color = MaterialTheme.colorScheme.onSecondaryContainer)
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.secondary)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "${matrix.rows} x ${matrix.cols}",
                fontSize = 19.sp,
                color = MaterialTheme.colorScheme.onSecondary
            )
        }
    }
}

@Composable
fun MatrixFuncRow(
    funcList : List<MatrixFunc<*>>,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    Row(
        modifier = modifier
            .horizontalScroll(scrollState)
            .padding(15.dp)
        ,
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        funcList.forEach{
            Box(
                modifier = Modifier
                    .clickable { }
                    .size(70.dp, 50.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(MaterialTheme.colorScheme.tertiary)

            ){
                Text(
                    text = it.name,
                    color = MaterialTheme.colorScheme.onTertiary,
                    modifier = Modifier.align(
                        Alignment.Center
                    )
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatrixConsole(
    msg: MutableState<String>,
    funcList : List<MatrixFunc<*>>,
    consoleMessages : List<String>,
    onSendMsg : () -> Unit,
    modifier: Modifier = Modifier
) {

    val scope = rememberCoroutineScope()
    Column(modifier = modifier
    ) {
        MatrixFuncRow(
            funcList,
            modifier = Modifier
                .fillMaxWidth()
                .border(BorderStroke(1.dp, MaterialTheme.colorScheme.primary))
        )

        val scrollState = rememberScrollState()


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
                .border(BorderStroke(1.dp, MaterialTheme.colorScheme.primary))
                .verticalScroll(scrollState)


        ) {
            consoleMessages.forEach{
                Text(
                    text = it,
                    maxLines = 2,
                    modifier = Modifier
                        .fillMaxWidth()
                        .bottomBorder( 1.dp, MaterialTheme.colorScheme.primary),
                    fontSize = 21.sp
                )
            }
        }
        
        Spacer(modifier = Modifier.height(20.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = msg.value,
                onValueChange = {
                                msg.value = it
                },
                label = {
                    Text("expression")
                }
            )
            
            Spacer(modifier = Modifier.padding(end=6.dp))

            Button(onClick = {
                onSendMsg()

                scope.launch {
                    scrollState.scrollTo(scrollState.maxValue)
                }

            }){
                Icon(Icons.Filled.Send,"")
            }
        }

        LaunchedEffect(key1 = Object()){
            scrollState.scrollTo(scrollState.maxValue)
        }


    }

}

fun Modifier.bottomBorder(strokeWidth: Dp, color: Color) = composed(
    factory = {
        val density = LocalDensity.current
        val strokeWidthPx = density.run { strokeWidth.toPx() }

        Modifier.drawBehind {
            val width = size.width
            val height = size.height - strokeWidthPx/2

            drawLine(
                color = color,
                start = Offset(x = 0f, y = height),
                end = Offset(x = width , y = height),
                strokeWidth = strokeWidthPx
            )
        }
    }
)