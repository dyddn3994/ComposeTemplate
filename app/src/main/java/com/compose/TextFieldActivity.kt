package com.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.ui.theme.ComposeTemplateTheme

class TextFieldActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTemplateTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TextFieldContainer()
                }
            }
        }
    }
}

// value: TextField 값
// onValueChange: 값 변경 이벤트
// enabled:
// isError
// leadingIcon: 좌측 아이콘(Composable)
// trailingIcon: 우측 아이콘(Composable)
// visualTransformation: 비밀번호 숨기기 처리
// singleLine: true면 한줄로만 입력
// maxLines: singleLine=false일때 몇 줄까지 허용할건지
// keyboardOptions:
// keyboardActions:

@Composable
fun TextFieldContainer() {

    val userInput = remember { mutableStateOf(TextFieldValue()) }

    Column (Modifier.padding(16.dp)) {
        TextField (
            value = userInput,
            onValueChange = { it -> userInput.value = it },
            label = ""
        ) {

        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview8() {
    ComposeTemplateTheme {
        TextFieldContainer()
    }
}