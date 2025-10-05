package diomaxius.whattocookwith.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun SearchOutlinedTextField(
    modifier: Modifier = Modifier,
    query: String,
    onQueryChange: (String) -> Unit,
    focusManager: FocusManager,
    shape: Shape = OutlinedTextFieldDefaults.shape,
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = query,
        onValueChange = { onQueryChange(it) },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
            }
        ),
        trailingIcon = {
            TrailingIcon(
                query = query,
                focusManager = focusManager,
                onQueryChange = onQueryChange
            )
        },
        shape = shape
    )
}

@Composable
private fun TrailingIcon(
    query: String,
    focusManager: FocusManager,
    onQueryChange: (String) -> Unit,
) {
    if (query.isNotEmpty()) IconButton(
        modifier = Modifier.padding(end = 6.dp),
        onClick = {
            onQueryChange("")
            focusManager.clearFocus()
        }
    ) {
        Icon(
            imageVector = Icons.Default.Clear,
            contentDescription = "Clear search"
        )
    } else Icon(
        modifier = Modifier.padding(end = 6.dp),
        imageVector = Icons.Default.Search,
        contentDescription = "Search"
    )
}