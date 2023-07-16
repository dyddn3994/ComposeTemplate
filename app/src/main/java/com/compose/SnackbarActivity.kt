package com.compose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.compose.ui.theme.ComposeTemplateTheme
import kotlinx.coroutines.launch

class SnackbarActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTemplateTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SnackbarContainer()
                }
            }
        }
    }
}

@Composable
fun SnackbarContainer() {

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    val buttonTitle: (SnackbarData?) -> String = { snackbarData ->
        if (snackbarData != null) "스낵바 숨기기"
        else "스낵바 보여주기"
    }

    Box (
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button (onClick = {
            if (snackbarHostState.currentSnackbarData != null) {
                Log.d("TAG", "SnackbarContainer: 이미 스낵바가 있음")
                snackbarHostState.currentSnackbarData?.dismiss()
                return@Button
            }
            coroutineScope.launch {
                snackbarHostState.showSnackbar(
                    message = "스낵바아ㅏ",
                    actionLabel = "확인", // 스낵바 내 버튼
                    duration = SnackbarDuration.Short
                ).let {
                    when (it) {
                        SnackbarResult.Dismissed -> Log.d("TAG", "SnackbarContainer: 스낵바 닫아짐")
                        SnackbarResult.ActionPerformed -> Log.d("TAG", "SnackbarContainer: 확인 버튼 눌러짐")
                    }
                }
            }
        }) {
            Text(buttonTitle(snackbarHostState.currentSnackbarData))
        }

        // 스낵바가 보여지는 부분
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview7() {
    ComposeTemplateTheme {
        SnackbarContainer()
    }
}