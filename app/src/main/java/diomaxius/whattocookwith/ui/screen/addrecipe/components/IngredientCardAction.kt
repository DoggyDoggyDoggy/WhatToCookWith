package diomaxius.whattocookwith.ui.screen.addrecipe.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import diomaxius.whattocookwith.domain.model.RecipeIngredient

@Composable
fun IngredientCardAction(
    recipeIngredient: RecipeIngredient,
    index: Int,
    onRecipeIngredientChangeQuantity: (Int, Int) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            modifier = Modifier.width(75.dp),
            value = if (recipeIngredient.requiredQuantity == 0) "" else recipeIngredient.requiredQuantity.toString(),
            onValueChange = {
                if (it.isNotEmpty() && it.isNotBlank()) {
                    if (it.length < 5 && it.all { char -> char.isDigit() })
                        onRecipeIngredientChangeQuantity(it.toInt(), index)
                }
                else onRecipeIngredientChangeQuantity(0, index)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            )
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(recipeIngredient.unit)
    }
}