// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import de.amirrocker.operationcomposedesktop.domain.initWorld
import de.amirrocker.operationcomposedesktop.ui.OperationXGame

//@Composable
//@Preview
//fun App() {
//    var text by remember { mutableStateOf("Hello, World!") }
//
//    MaterialTheme {
//        Button(onClick = {
//            text = "Hello, Desktop!"
//        }) {
//            Text(text)
//        }
//    }
//}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Operation X",
        state = WindowState(width = 800.dp, height = 800.dp)
    ) {
//        OperationXGame()

        initWorld()

    }
}



































