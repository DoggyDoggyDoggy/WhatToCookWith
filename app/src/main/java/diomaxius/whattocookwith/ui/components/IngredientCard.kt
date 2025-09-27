package diomaxius.whattocookwith.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import diomaxius.whattocookwith.domain.model.Ingredient

@Composable
fun IngredientCard(
    ingredient: Ingredient
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
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
                fontSize = 20.sp
            )

            Spacer(
                modifier = Modifier.weight(1f)
            )

            Icon(
                modifier = Modifier.size(32.dp),
                imageVector = Icons.Default.Remove,
                contentDescription = null
            )

            Spacer(
                modifier = Modifier.width(6.dp)
            )

            Text(
                text = ingredient.quantity.toString()
            )

            Spacer(
                modifier = Modifier.width(6.dp)
            )

            Icon(
                modifier = Modifier.size(32.dp),
                imageVector = Icons.Default.Add,
                contentDescription = null
            )

            Spacer(
                modifier = Modifier.width(6.dp)
            )
        }
    }
}