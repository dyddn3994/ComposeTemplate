package com.compose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
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
                    Column(
                        modifier = Modifier.padding(horizontal = 20.dp)
                    ) {
                        moveActivityBtn({startActivity(Intent(context, RowAndColumn::class.java))}, "Row & Column")
                        moveActivityBtn({startActivity(Intent(context, BoxActivity::class.java))}, "Box")
                        moveActivityBtn({startActivity(Intent(context, TextActivity::class.java))}, "Text")
                        moveActivityBtn({startActivity(Intent(context, ShapeActivity::class.java))}, "Shape")
                        moveActivityBtn({startActivity(Intent(context, ButtonActivity::class.java))}, "Button")
                        moveActivityBtn({startActivity(Intent(context, CheckboxActivity::class.java))}, "Checkbox")
                        moveActivityBtn({startActivity(Intent(context, SnackbarActivity::class.java))}, "Snackbar")
                        moveActivityBtn({startActivity(Intent(context, TextFieldActivity::class.java))}, "TextField")
                    }
                }
            }
        }
    }
}

@Composable
fun moveActivityBtn(buttonClick: () -> Unit, text: String) {
    Button (
        onClick = buttonClick,
        modifier = Modifier
            .fillMaxWidth(),

    ) {
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