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
import diomaxius.whattocookwith.ui.screen.addrecipe.RECIPE_Instructions_PLACEHOLDER

@Composable
fun RecipeInstructions(
    recipeInstructions: String,
    onRecipeInstructionsChange: (String) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Recipe instructions:",
            fontSize = 22.sp,
            fontWeight = FontWeight.Medium
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            minLines = 16,
            value = recipeInstructions,
            onValueChange = { onRecipeInstructionsChange(it) },
            placeholder = { Text(RECIPE_Instructions_PLACEHOLDER) }
        )
    }
}