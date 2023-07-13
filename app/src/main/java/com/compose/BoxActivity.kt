package com.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.ui.theme.ComposeTemplateTheme
import kotlin.random.Random

class BoxActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTemplateTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BoxWithConstraintContainer()
                }
            }
        }
    }
}

// 박스는 겹칠 수 있다.
// 기존 relative layout, constraint layout, frame layout과 같이 뷰를 겹칠 수 있음
// 겹치는 순서에 주의(배경이 되는 가장 큰 박스가 먼저 나와야 함)

// alignment
// row, column보다 다양하게 지원
// Alignment.BottomCenter: 컨테이너의 아래 중앙
// Alignment.BottomStart: 컨테이너의 아래 왼쪽
// Alignment.BottomEnd: 컨테이너의 아래 오른쪽
// Alignment.Center: 컨테이너의 정 중앙
// Alignment.CenterStart: 컨테이너의 중앙 왼쪽
// Alignment.CenterEnd: 컨테이너의 중앙 오른쪽
// Alignment.TopCenter: 컨테이너의 위 중앙
// Alignment.TopStart: 컨테이너의 위 왼쪽
// Alignment.topEnd: 컨테이너의 위 오른쪽

// propagateMinConstraints 해당 옵션을 true로 하면
// 박스 안에 있는 제일 작은 크기의 뷰를 컨테이너 박스의 크기만큼 constarint를 건다(꽉 채움)

@Composable
fun BoxContainer() {
    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
//        propagateMinConstraints = true
    ) {
        DummyBox2(modifier = Modifier.size(200.dp), color = Color.Green)
        DummyBox2(modifier = Modifier.size(150.dp), color = Color.Yellow)
        DummyBox2(color = Color.Blue)
    }
}

// BoxWithConstraints
// 조건에 따라 다른 Box를 출력할 수 있음

@Composable
fun BoxWithConstraintContainer() {
    BoxWithConstraints(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // 화면이 회전되는 등 Box 크기 변화에 따라 조건문 실행
//        if (this.minHeight > 400.dp) {
//            DummyBox2(modifier = Modifier.size(200.dp), color = Color.Green)
//        }
//        else {
//            DummyBox2(modifier = Modifier.size(200.dp), color = Color.Yellow)
//        }
//        Text(text = "minHeight: ${this.minHeight}")

        Column() {
            BoxWithConstraintItem(modifier = Modifier
                .size(200.dp)
                .background(Color.Yellow)
            )
            BoxWithConstraintItem(modifier = Modifier
                .size(300.dp)
                .background(Color.Green)
            )
        }
    }
}

@Composable
fun BoxWithConstraintItem(modifier: Modifier = Modifier) {
    BoxWithConstraints(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        if (this.minWidth > 200.dp) {
            Text(text = "이것은 큰 상자이다")
        }
        else {
            Text(text = "이것은 작은 상자이다")
        }

    }
}


@Composable
fun DummyBox2(modifier: Modifier = Modifier, color: Color? = null) {
    val red = Random.nextInt(256)
    val green = Random.nextInt(256)
    val blue = Random.nextInt(256)

    // color값이 있으면 해당 값을 넣어주고 없으면 랜덤값
    val randomColor = color ?: Color(red, green, blue)

    Box(modifier = modifier
        .size(100.dp)
        .background(randomColor))
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    ComposeTemplateTheme {
//        BoxContainer()
        BoxWithConstraintContainer()
    }
}