package diomaxius.whattocookwith.ui.screen.addrecipe.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import diomaxius.whattocookwith.ui.screen.addrecipe.RECIPE_NAME_PLACEHOLDER

@Composable
fun RecipeName(
    recipeName: String,
    onRecipeNameChange: (String) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Recipe name:",
            fontSize = 22.sp,
            fontWeight = FontWeight.Medium
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = recipeName,
            onValueChange = { onRecipeNameChange(it) },
            placeholder = { Text(RECIPE_NAME_PLACEHOLDER) }
        )
    }
}