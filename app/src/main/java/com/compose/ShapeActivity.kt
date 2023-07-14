package com.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.compose.ui.theme.ComposeTemplateTheme
import kotlin.math.cos
import kotlin.math.sin

class ShapeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTemplateTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ShapeContainer()
                }
            }
        }
    }
}

// box의 형태를 지정하여 여러 모양을 만듦
// 사각형: RectangleShape
// 원: CircleShape
// 모서리가 둥근 사각형: RoundedCornerShape(size)
// 팔각형(모서리를 자른 사각형): CutCornerShape(size)
@Composable
fun ShapeContainer() {

    var polySides by remember { mutableStateOf(3) }

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        DummyBox(modifier = Modifier.clip(RectangleShape))
//        DummyBox(modifier = Modifier.clip(CircleShape))
//        DummyBox(modifier = Modifier.clip(RoundedCornerShape(10.dp)))
//        DummyBox(modifier = Modifier.clip(CutCornerShape(10.dp))) // 8각형
//        DummyBox(modifier = Modifier.clip(TriangleShape()))

        // 버튼을 누름에 따라 polySize증가, 도형 변의 개수 증가
        DummyBox(modifier = Modifier.clip(PolyShape(polySides, 100f)))
        Text(text = "polySize: $polySides")
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = {
                polySides++
            }) {
                Text(text = "polySize + 1")
            }
            Button(onClick = {
                polySides = 3
            }) {
                Text(text = "polySize = 3")
            }
        }
    }
}

// 직접 설정한 shape
// Path에서 moveTo와 lineTo로 Box 형태에서 직접 자름
class TriangleShape(): Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            moveTo(size.width / 2f, 0f) // 중앙 꼭대기로 이동
            lineTo(size.width, size.height) // 박스 우측 하단으로 이동
            lineTo(0f, size.height) // 박스 좌측 하단으로 이동
            close() // 시작점과 마지막 부분 잇고 종료
        }
        return Outline.Generic(path = path)
    }
}

// 변의 수와 크기를 지정하여 도형 생성하는 방법
class PolyShape(private val sides: Int, private val radius: Float): Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(path = Path().apply { this.polygon(sides, radius, size.center) })
    }
}
fun Path.polygon(sides: Int, radius: Float, center: Offset) {
    val angle = 2.0 * Math.PI / sides
    moveTo(
        x = center.x + (radius * cos(0.0)).toFloat(),
        y = center.y + (radius * sin(0.0)).toFloat()
    )
    for (i in 1 until sides) {
        lineTo(
            x = center.x + (radius * cos(angle * i)).toFloat(),
            y = center.y + (radius * sin(angle * i)).toFloat()
        )
    }
    close()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    ComposeTemplateTheme {
        ShapeContainer()
    }
}