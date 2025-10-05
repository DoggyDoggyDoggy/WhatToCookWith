package diomaxius.whattocookwith.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.ImeAction

@Composable
fun SearchOutlinedTextField(
    modifier: Modifier = Modifier,
    query: String,
    onQueryChange: (String) -> Unit,
    focusManager: FocusManager,
    shape: Shape = OutlinedTextFieldDefaults.shape
) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth(),
        value = query,
        onValueChange = {onQueryChange(it)},
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
            }
        ),
        shape = shape
    )
}