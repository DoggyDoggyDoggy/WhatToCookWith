package diomaxius.whattocookwith.ui.components.ingredientcard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import diomaxius.whattocookwith.domain.model.Ingredient

@Composable
fun IngredientCard(
    ingredient: Ingredient,
    ingredientCardMode: IngredientCardMode,
    deleteIngredient: (Ingredient) -> Unit
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

            if (ingredientCardMode is IngredientCardMode.IngredientList) {
                Icon(
                    modifier = Modifier
                        .size(42.dp)
                        .clickable{
                            deleteIngredient(ingredient)
                        }
                    ,
                    imageVector = Icons.Default.Delete,
                    tint = Color(0xFFE36363),
                    contentDescription = "Delete ingredient"
                )
            } else if (
                ingredientCardMode is IngredientCardMode.PantryList
            ) {
                Icon(
                    modifier = Modifier.size(42.dp),
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
                    modifier = Modifier.size(42.dp),
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }

            Spacer(
                modifier = Modifier.width(6.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun IngredientCardPreview() {
    IngredientCard(
        ingredient = Ingredient(
            name = "Egg",
            quantity = 1,
            unit = "pcs"
        ),
        ingredientCardMode = IngredientCardMode.IngredientList,
        deleteIngredient = {}
    )
}