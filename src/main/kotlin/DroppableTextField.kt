import androidx.compose.foundation.border
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.onExternalDrag
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DroppableTextField() {
    var isDroppable by remember { mutableStateOf(false) }
    val (textFieldValue, setTextFieldValue) = remember { mutableStateOf(TextFieldValue()) }

    TextField(
        value = textFieldValue,
        onValueChange = setTextFieldValue,
        modifier = Modifier
            .border(4.dp, if (isDroppable) Color.Green else Color.Red)
            .onExternalDrag(
                onDragStart = { externalDragValue ->
                    isDroppable = externalDragValue.dragData is androidx.compose.ui.DragData.Text
                },
                onDragExit = {
                    isDroppable = false
                },
                onDrop = { externalDragValue ->
                    isDroppable = false
                    val dragData = externalDragValue.dragData
                    if (dragData is androidx.compose.ui.DragData.Text) {
                        setTextFieldValue(textFieldValue.copy(
                            text = textFieldValue.text.substring(0, textFieldValue.selection.start) +
                                    dragData.readText() +
                                    textFieldValue.text.substring(textFieldValue.selection.end),
                            selection = TextRange(textFieldValue.selection.end)
                        ))
                    }
                }
            )
    )
}
