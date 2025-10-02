package diomaxius.whattocookwith.ui.components.ingredientcard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import diomaxius.whattocookwith.domain.model.Ingredient

@Composable
fun PantryList(
    ingredient: Ingredient,
    increaseQuantity: (Ingredient) -> Unit,
    decreaseQuantity: (Ingredient) -> Unit,
) {
    Icon(
        modifier = Modifier
            .size(42.dp)
            .clickable {
                decreaseQuantity(ingredient)
            },
        imageVector = Icons.Default.Remove,
        contentDescription = null
    )

    Spacer(
        modifier = Modifier.width(6.dp)
    )

    Text(
        text = ingredient.quantity.toString(),
        fontSize = 22.sp,
        fontWeight = FontWeight.Medium
    )

    Spacer(
        modifier = Modifier.width(6.dp)
    )

    Icon(
        modifier = Modifier
            .size(42.dp)
            .clickable {
                increaseQuantity(ingredient)
            },
        imageVector = Icons.Default.Add,
        contentDescription = null
    )
}