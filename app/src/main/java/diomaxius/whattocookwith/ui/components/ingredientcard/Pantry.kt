package diomaxius.whattocookwith.ui.components.ingredientcard

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import diomaxius.whattocookwith.domain.model.Ingredient

@Composable
fun Pantry(
    ingredient: Ingredient
) {
    Text(
        text = "${ingredient.quantity} ${ingredient.unit}",
        fontSize = 22.sp,
        fontWeight = FontWeight.Medium
    )
}