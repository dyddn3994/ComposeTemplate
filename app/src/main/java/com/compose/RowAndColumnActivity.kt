package com.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.ui.theme.ComposeTemplateTheme
import kotlin.random.Random

class RowAndColumn : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTemplateTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RowContainer()
                    ColumnContainer()
                }
            }
        }
    }
}

// arrangement
// 요소를 어떤식으로 배열할지 (flex같은 느낌)
// 컨테이너 성격의 Composable에서 요소들의 아이템을 정렬할 때 사용
// Arrangement.Start(Top): 왼쪽으로(위로)
// Arrangement.End(Bottom): 오른쪽으로(아래로)
// Arrangement.Center: 중앙으로
// Arrangement.SpaceBetween: 공간 모두 차지
// Arrangement.SpaceAround: 요소 당 일정한 빈 공간 차지
// Arrangement.SpaceEvenly: 요소들 사이 및 바깥에 빈 공간을 같게 하기

// alignment
// 해당 컨테이너 안에 들어있는 요소의 위치를 어떤 방향으로 둘지
// LinearLayout에서 gravity와 같음
// Alignment.Top(Start): 컨테이너 위(왼쪽)에 두기
// Alignment.Bottom(End): 컨테이너 아래(오른쪽)에 두기
// Alignment.CenterVertically(CenterHorizontally): 컨테이너의 수직방향으로 중앙에 두기

// column과 row에 따라 거꾸로 설정하면 됨

@Composable
fun RowContainer() {
    Row(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        DummyBox()
        DummyBox()
        DummyBox()
    }
}

@Composable
fun ColumnContainer() {
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.End
    ) {
        DummyBox()
        DummyBox()
        DummyBox()
    }
}

@Composable
fun DummyBox(modifier: Modifier = Modifier) {
    val red = Random.nextInt(256)
    val green = Random.nextInt(256)
    val blue = Random.nextInt(256)
    val randomColor = Color(red, green, blue)
    Box(modifier = modifier
        .size(100.dp)
        .background(randomColor))
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeTemplateTheme {
//        RowContainer()
        ColumnContainer()
    }
}