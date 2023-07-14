package com.compose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.ui.theme.ComposeTemplateTheme

private const val TAG = "TextActivity"
class TextActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTemplateTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TextContainer()
                }
            }
        }
    }
}

@Composable
fun TextContainer() {
    var words = stringResource(id = R.string.dummy_short_text)
    var wordsArray = words.split(" ")

    val scrollState = rememberScrollState() // 스크롤 상태

    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()
            .verticalScroll(scrollState) // 스크롤 설정
    ) {
        val name = "정요우"
        Text(
            text = "안녕하세요 ${name}",
            style = TextStyle(
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Yellow)
        )
        Text(
            text = "안녕하세요 ${name}",
            style = TextStyle(
                textAlign = TextAlign.Start
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Yellow)
        )
        Text(
            text = "안녕하세요 ${name}",
            style = TextStyle(
                textAlign = TextAlign.End
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Yellow)
        )
        Text(
            text = stringResource(R.string.dummy_short_text),
            style = TextStyle(
                textAlign = TextAlign.End
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Yellow)
        )
        Text(
            text = stringResource(id = R.string.dummy_long_text), // res/values/strings.xml에 있는 데이터 조회
            maxLines = 3, // 최대 텍스트 라인 수. 라인 수를 넘어가는 텍스트는 잘림
            overflow = TextOverflow.Visible, // 영역을 넘어가는 텍스트에 대한 처리를 어떻게 할지 설정. Ellipsis: 문장 마지막 ...으로 처리, Visible: 넘는 부분도 보여줌. maxLines를 무시하지는 않음, Clip: default. 넘는 부분을 자름
            style = TextStyle(
                textAlign = TextAlign.Justify, // 정렬
                textDecoration = TextDecoration.combine( // text에 실선 등 추가, 여러 스타일을 동시에 설정할 때 TextDecoration.combine 사용
                    listOf(
                        TextDecoration.Underline,
                        TextDecoration.LineThrough
                    )
                ),
                fontFamily = FontFamily(Font(R.font.pretendard_variable, weight = FontWeight.ExtraBold)), // 폰트 설정, 기본 제공된 폰트 사용 시에는 FontFamily.~~만 사용
                lineHeight = 40.sp // 줄 간격
            ),
            fontWeight = FontWeight.W200, // 폰트 굵기 설정
            fontSize = 20.sp, // 폰트 크기
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Yellow)
        )
        Text(text = buildAnnotatedString {
            append("안녕하세요")

            // 해당 스타일의 블럭 생성
            withStyle(style = SpanStyle(
                color = Color.Blue,
                fontSize = 40.sp,
                fontWeight = FontWeight.ExtraBold,
            )
            ) {
                append("잠와 뒤지겄어요")
            }
            append("으어")
        })
        Text(text = buildAnnotatedString {
            wordsArray.forEach {
                if (it.contains("풀이")) {
                    withStyle(style = SpanStyle(
                        color = Color.Red,
                        fontSize = 40.sp,
                        fontWeight = FontWeight.ExtraBold,
                    )
                    ) {
                        append("$it ")
                    }
                }
                else {
                    append("$it ")
                }
            }
        })
        ClickableText(
            text = AnnotatedString("클릭미!"),
            onClick = {
                Log.d(TAG, "TextContainer: 클릭미")
            }
        )
        Text(
            text = stringResource(id = R.string.dummy_long_text),
            style = TextStyle(lineHeight = 20.sp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    ComposeTemplateTheme {
        TextContainer()
    }
}