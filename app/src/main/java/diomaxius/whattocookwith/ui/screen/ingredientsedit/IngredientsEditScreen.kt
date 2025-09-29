package diomaxius.whattocookwith.ui.screen.ingredientsedit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import diomaxius.whattocookwith.ui.components.DropdownMenu

@Composable
fun IngredientsEditScreen(
    viewModel: IngredientsEditScreenViewModel = hiltViewModel()
) {
    val ingredient by viewModel.ingredient.collectAsState()
    val unit by viewModel.unit.collectAsState()

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column (
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            OutlinedTextField(
                value = ingredient,
                onValueChange = {
                    viewModel.onIngredientChange(it)
                },
                label = { Text("Ingredient") },
                placeholder = { Text("Egg") }
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Choose unit:",
                    textAlign = TextAlign.Start
                )

                Spacer(modifier = Modifier.width(12.dp))

                DropdownMenu(
                    selected = unit,
                    selection = listOf("pcs", "ml", "g"),
                    onUnitSelected = { unit ->
                        viewModel.onUnitChange(unit)
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = { viewModel.saveIngredient() }
        ) {
            Text(text = "Save")
        }
    }
}