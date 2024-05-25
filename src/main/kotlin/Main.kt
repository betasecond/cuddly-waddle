import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import java.io.File

@Composable
@Preview
fun App() {
    var inputPath by remember { mutableStateOf("拖入 JSON 文件或输入路径") }
    val userHome = System.getProperty("user.home")
    var outputPath by remember { mutableStateOf("$userHome/Downloads/output.ics") }
    var alertMessage by remember { mutableStateOf<String?>(null) }

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
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            try {
                val processResult: ProcessResult = MainProcess.mainUiArgs(inputPath, outputPath)
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
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "JSON 到 ICS 转换器") {
        App()
    }
}
