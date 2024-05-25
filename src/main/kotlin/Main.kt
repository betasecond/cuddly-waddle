import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import epicarchitect.calendar.compose.basis.BasisEpicCalendar
import epicarchitect.calendar.compose.basis.addMonths
import epicarchitect.calendar.compose.basis.config.rememberMutableBasisEpicCalendarConfig
import epicarchitect.calendar.compose.basis.state.rememberMutableBasisEpicCalendarState
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.toJavaLocalDate
import java.io.File

@Composable
@Preview
fun App() {
    var inputPath by remember { mutableStateOf("输入 JSON 文件路径") }
    val userHome = System.getProperty("user.home")
    var outputPath by remember { mutableStateOf("$userHome/Downloads/output.ics") }
    var alertMessage by remember { mutableStateOf<String?>(null) }
    var selectedDate by remember { mutableStateOf(LocalDate.parse("2024-01-01")) }

    val calendarConfig = rememberMutableBasisEpicCalendarConfig(
        rowsSpacerHeight = 4.dp,
        dayOfWeekViewHeight = 40.dp,
        dayOfMonthViewHeight = 40.dp,
        columnWidth = 40.dp,
        displayDaysOfAdjacentMonths = true,
        displayDaysOfWeek = true
    )
    val calendarState = rememberMutableBasisEpicCalendarState(
        config = calendarConfig
    )
    if (alertMessage != null) {
        AlertDialog(
            onDismissRequest = { alertMessage = null },
            title = { Text("系统消息") },
            text = { Text(alertMessage!!) },
            confirmButton = {
                Button(onClick = { alertMessage = null }) {
                    Text("确定")
                }
            }
        )
    }



    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            TextField(
                value = inputPath,
                onValueChange = { inputPath = it },
                modifier = Modifier.weight(1f).padding(end = 8.dp),
                label = { Text("输入文件路径") }
            )
            Button(onClick = {
                val fileChooser = javax.swing.JFileChooser()
                fileChooser.dialogTitle = "选择 JSON 文件"
                val result = fileChooser.showOpenDialog(null)
                if (result == javax.swing.JFileChooser.APPROVE_OPTION) {
                    val selectedFile: File = fileChooser.selectedFile
                    inputPath = selectedFile.absolutePath
                }
            }) {
                Text("选择文件")
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = outputPath,
            onValueChange = { outputPath = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("输出文件路径") }
        )
        Button(onClick = {
            try {
                // Convert selectedDate to java.time.LocalDate for MainProcess.mainUiArgs
                val javaSelectedDate = selectedDate.toJavaLocalDate()
                val processResult: ProcessResult = MainProcess.mainUiArgs(inputPath, outputPath, javaSelectedDate)
                alertMessage = if (processResult.success) {
                    "ICS 文件已成功生成!"
                } else {
                    "系统错误: ${processResult.message}"
                }
            } catch (e: Exception) {
                alertMessage = "系统错误: ${e.message}"
            }
        }) {
            Text("生成 ICS 文件")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text("选择的日期: $selectedDate")
        Text("目前所在月份: ${calendarState.currentMonth.year}  ${calendarState.currentMonth.month}")
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
//            Button(onClick = { calendarState.currentMonth.addMonths(1) }) {
//                Text("上个月")
//            }
//            Button(onClick = { calendarState.currentMonth.addMonths(-1) }) {
//                Text("下个月")
//            }
            Button(onClick = { calendarState.currentMonth = calendarState.currentMonth.addMonths(Operator.PrevMonth.months) }) {
                Text("上个月")
            }
            Button(onClick = { calendarState.currentMonth = calendarState.currentMonth.addMonths(Operator.NextMonth.months) }) {
                Text("下个月")
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        BasisEpicCalendar(
            state = calendarState,
            onDayOfMonthClick = { date -> selectedDate = date }
        )

        Spacer(modifier = Modifier.height(16.dp))

    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "JSON 到 ICS 转换器") {
        App()
    }
}

private enum class Operator(val months: Int){
    NextMonth(1),
    PrevMonth(-1)
}
