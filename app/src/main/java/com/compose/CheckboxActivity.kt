package com.compose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.ui.theme.ComposeTemplateTheme

private const val TAG = "CheckboxActivity"
class CheckboxActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTemplateTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CheckboxContainer()
                }
            }
        }
    }
}

// checked: 체크 상태값
// onCheckedChange: 체크 상태 변경 콜백 이벤트
// enabled: 체크 가능 여부
// colors: 색 지정
@Composable
fun CheckboxContainer() {

    // checkbox의 상태를 저장
val checkedStatusForFirst = remember { mutableStateOf(false) }
var checkedStatusForSecond by remember { mutableStateOf(false) }
val (checkedStatusForThird, setCheckedStatusForThird) = remember { mutableStateOf(false) }

    val checkedStatus1 = remember { mutableStateOf(false) }
    val checkedStatus2 = remember { mutableStateOf(false) }
    val checkedStatus3 = remember { mutableStateOf(false) }
    val checkedStatusArray = arrayOf (
        checkedStatus1,
        checkedStatus2,
        checkedStatus3,
    )

    val checkedStatusForAll = checkedStatusArray.all { it.value }
    val allBoxCheck: (Boolean) -> Unit = { isChecked ->
        checkedStatusArray.forEach {
            it.value = isChecked
        }
    }

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        CheckBoxMutableState("1번 확인사항", checkedStatusForFirst)
//        CheckBoxDelegate("2번 확인사항", checkedStatusForSecond) { checkedStatusForSecond = it }
//        CheckBoxDelegate("3번 확인사항", checkedStatusForThird, setCheckedStatusForThird)
        CheckBoxMutableState("1번 확인사항", checkedStatus1)
        CheckBoxMutableState("2번 확인사항", checkedStatus2)
        CheckBoxMutableState("3번 확인사항", checkedStatus3)
        Spacer(modifier = Modifier.height(10.dp))
        CheckBoxDelegate("모두 동의하십니까?", checkedStatusForAll, allBoxCheck)
        Spacer(modifier = Modifier.height(10.dp))
        CustomCheckBox("커스텀 체크박스입니다.")
//        Checkbox(
//            checked = checkedStatusForFirst.value,
//            onCheckedChange = { isChecked ->
//                Log.d(TAG, "CheckboxContainer: $isChecked")
//                checkedStatusForFirst.value = isChecked
//            }
//        )
//        Checkbox(
//            enabled = false,
//            checked = checkedStatusForSecond,
//            onCheckedChange = { isChecked ->
//                Log.d(TAG, "CheckboxContainer: $isChecked")
//                checkedStatusForSecond = isChecked
//            }
//        )
//        Checkbox(
//            enabled = false,
//            colors = CheckboxDefaults.colors(
//                checkedColor = Color.Red, // 체크된 상태 색
//                uncheckedColor = Color(0xFFB39DDB), // 체크되지 않은 상태(테두리 색)
//                checkmarkColor = Color.Black, //  체크된 상태의 체크모양 색
//                disabledCheckedColor = Color.Yellow, // enabled = false일 때 색
//            ),
//            checked = checkedStatusForThird,
//            onCheckedChange = { isChecked ->
//                Log.d(TAG, "CheckboxContainer: $isChecked")
//                setCheckedStatusForThird.invoke(isChecked)
//            }
//        )
    }
}

@Composable
fun CheckBoxMutableState(title: String, checkedStatus: MutableState<Boolean>) {
    Row(
        modifier = Modifier
            .padding(horizontal = 30.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checkedStatus.value,
            onCheckedChange = { isChecked ->
                checkedStatus.value = isChecked
            }
        )
        Text(text = title)
    }
}

@Composable
fun CheckBoxDelegate(title: String, checkedStatus: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .padding(horizontal = 30.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checkedStatus,
            onCheckedChange = onCheckedChange
        )
        Text(text = title)
    }
}

@Composable
fun CustomCheckBox(title: String) {

    var isChecked by remember { mutableStateOf(false) }

    val togglePainter = if (isChecked) R.drawable.ic_checked else R.drawable.ic_unchecked

    Row(
        modifier = Modifier
            .padding(horizontal = 30.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(60.dp)
                .clickable(
                    indication = rememberRipple ( // Image에 값을 주면 리플이 이미지 크기만큼만 떠서 제대로 나타나지 않음. 밖을 Box로 감싸서 처리
                        radius = 20.dp, // 리플 크기
                        bounded = false, // false이면 리플이 중앙에서 시작, true면 누른 위치에서 시작
                        color = Color.Blue, // 리플 색
                    ),
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    isChecked = !isChecked
                },
        ) {
            Image(
//                modifier = Modifier.clickable(
//                    indication = rememberRipple ( // 클릭할 때 애니메이션(리플), 제거하려면 null
//                        radius = 20.dp
//                    ),
//                    interactionSource = remember { MutableInteractionSource() }
//                ) {
//                    isChecked = !isChecked
//                },
                painter = painterResource(id = togglePainter),
                contentDescription = null
            )
        }
        Text(text = title)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview6() {
    ComposeTemplateTheme {
        CheckboxContainer()
    }
}