package diomaxius.whattocookwith.ui.components.ingredientcard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import diomaxius.whattocookwith.domain.model.Ingredient

@Composable
fun EditableIngredient(
    ingredient: Ingredient,
    deleteIngredient: (Ingredient) -> Unit,
    editIngredient: (Ingredient) -> Unit,
) {
    Icon(
        modifier = Modifier
            .size(42.dp)
            .clickable {

            },
        imageVector = Icons.Default.Edit,
        contentDescription = "Edit ingredient"
    )

    Icon(
        modifier = Modifier
            .size(42.dp)
            .clickable {
                deleteIngredient(ingredient)
            },
        imageVector = Icons.Default.Delete,
        tint = Color(0xFFE36363),
        contentDescription = "Delete ingredient"
    )
}