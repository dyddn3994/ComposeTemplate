package com.compose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.compose.ui.theme.ComposeTemplateTheme

private const val TAG = "MainActivity"
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val context = LocalContext.current as MainActivity
            ComposeTemplateTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Column() {
                        moveActivityBtn({startActivity(Intent(context, RowAndColumn::class.java))}, "Row & Column")
                        moveActivityBtn({startActivity(Intent(context, BoxActivity::class.java))}, "Box")
                        moveActivityBtn({startActivity(Intent(context, TextActivity::class.java))}, "Text")
                    }
                }
            }
        }
    }
}

@Composable
fun moveActivityBtn(buttonClick: () -> Unit, text: String) {
    Button (onClick = buttonClick) {
        Text(text)
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    ComposeTemplateTheme {
//        Greeting("Android")
//    }
//}