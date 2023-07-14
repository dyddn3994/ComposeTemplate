package com.compose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.compose.ui.theme.ComposeTemplateTheme

private const val TAG = "ButtonActivity"
class ButtonActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTemplateTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ButtonContainer()
                }
            }
        }
    }
}

// enable: 클릭이 가능한지 여부
// interactionSource: 사용자의 인터랙션 처리
// elevation: 그림자

// 커스텀 그림자 넣는 법
// shape: 모양
// border: 테두리
// colors: 버튼 색 지정
// contentPadding: padding 크기 설정

@Composable
fun ButtonContainer() {

    val buttonBorderGradient = Brush.horizontalGradient(listOf(Color.Yellow, Color.Red))

    // 눌러지는 사용자에 대한 상태
    val interactionSource = remember { MutableInteractionSource() }
    val interactionSourceForSecondBtn = remember { MutableInteractionSource() }

    // 사용자가 버튼을 눌렀을때의 상태를 담음
    val isPressed by interactionSource.collectIsPressedAsState()
    val isPressedForSecondBtn by interactionSourceForSecondBtn.collectIsPressedAsState()

    val pressStatusTitle = if (isPressed) "버튼을 누르고 있다." else "버튼에서 손을 뗐다."
    val pressedBtnRadius = if (isPressedForSecondBtn) 0.dp else 20.dp

    // 색 그림자 클릭에 따른 애니메이션
    val pressedBtnRadiusWithAnim: Dp by animateDpAsState(targetValue = pressedBtnRadius)

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            enabled = false,
            onClick = {
                Log.d(TAG, "ButtonContainer: 버튼1 클릭")
            }
        ) {
            Text(text = "버튼1")
        }
        Button(
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 10.dp, // 기본 그림자
                pressedElevation = 0.dp, // 버튼을 눌렀을 때 그림자
                disabledElevation = 0.dp // enabled가 false일때 그림자
            ),
            onClick = {
                Log.d(TAG, "ButtonContainer: 버튼2 클릭")
            }
        ) {
            Text(text = "버튼2")
        }
        Button(
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp),
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(4.dp, Color.Red),
            onClick = {
                Log.d(TAG, "ButtonContainer: 버튼3 클릭")
            }
        ) {
            Text(text = "버튼3")
        }
        Button(
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp),
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(4.dp, buttonBorderGradient),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                disabledContainerColor = Color.LightGray // enabled = false 일 때 색상
            ),
            onClick = {
                Log.d(TAG, "ButtonContainer: 버튼4 클릭")
            }
        ) {
            Text(text = "버튼4")
        }
        Button(
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp),
            shape = RoundedCornerShape(10.dp),
            contentPadding = PaddingValues(horizontal = 5.dp, vertical = 20.dp),
            interactionSource = interactionSource,
            onClick = {
                Log.d(TAG, "ButtonContainer: 버튼5 클릭")
            }
        ) {
            Text(text = "버튼5")
        }
        Text(text = pressStatusTitle)

        Button(
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp),
            shape = RoundedCornerShape(10.dp),
            contentPadding = PaddingValues(horizontal = 5.dp, vertical = 20.dp),
            interactionSource = interactionSourceForSecondBtn,
            modifier = Modifier.coloredShadow( // Extensions.kt에서 설정한 색상 그림자 값
                color = Color.Red,
                alpha = 0.5f,
                borderRadius = 10.dp, // border radius가 있는 shape에서 해당 값을 0.dp로 주면 그림자 애니메이션이 이상하게 나옴
                shadowRadius = pressedBtnRadiusWithAnim,
                offsetY = 0.dp,
                offsetX = 0.dp
            ),
            onClick = {
                Log.d(TAG, "ButtonContainer: 버튼6 클릭")
            }
        ) {
            Text(text = "버튼6")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview5() {
    ComposeTemplateTheme {
        ButtonContainer()
    }
}