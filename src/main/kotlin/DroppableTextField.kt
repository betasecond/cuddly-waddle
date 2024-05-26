// DroppableTextField.kt
import androidx.compose.foundation.border
import androidx.compose.material3.TextField
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.onExternalDrag
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import java.net.URI
import java.nio.file.Paths

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DroppableTextField(
    path: String,
    onPathChange: (String) -> Unit
) {
    var isDroppable by remember { mutableStateOf(false) }
    var textFieldValue by remember { mutableStateOf(TextFieldValue(path)) }

    TextField(
        value = textFieldValue,
        onValueChange = { newValue ->
            textFieldValue = newValue
            onPathChange(newValue.text)
        },
        modifier = Modifier
            .border(4.dp, if (isDroppable) Color.Green else Color.Red)
            .onExternalDrag(
                onDragStart = { externalDragValue ->
                    isDroppable = externalDragValue.dragData is androidx.compose.ui.DragData.FilesList
                },
                onDragExit = {
                    isDroppable = false
                },
                onDrop = { externalDragValue ->
                    isDroppable = false
                    val dragData = externalDragValue.dragData
                    if (dragData is androidx.compose.ui.DragData.FilesList) {
                        val fileUris = dragData.readFiles()
                        if (fileUris.isNotEmpty()) {
                            val fileUri = fileUris[0]
                            try {
                                val filePath = Paths.get(URI(fileUri)).toString()
                                textFieldValue = TextFieldValue(filePath)
                                onPathChange(filePath)
                            } catch (e: Exception) {
                                // Handle the exception if needed
                                e.printStackTrace()
                            }
                        }
                    }
                }
            )
    )
}
