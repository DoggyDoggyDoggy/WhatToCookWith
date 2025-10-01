package diomaxius.whattocookwith.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import diomaxius.whattocookwith.domain.model.Ingredient

@Composable
fun IngredientCard(
    ingredient: Ingredient,
    actions: @Composable RowScope.(Ingredient) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .padding(bottom = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(
                modifier = Modifier.width(6.dp)
            )

            Text(
                text = ingredient.name,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )

            Spacer(
                modifier = Modifier.weight(1f)
            )

            actions(ingredient)

            Spacer(
                modifier = Modifier.width(6.dp)
            )
        }
    }
}

@Composable
fun IngredientList(
    deleteIngredient: () -> Unit,
) {
    Icon(
        modifier = Modifier
            .size(42.dp)
            .clickable {
                deleteIngredient()
            },
        imageVector = Icons.Default.Delete,
        tint = Color(0xFFE36363),
        contentDescription = "Delete ingredient"
    )
}

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