package diomaxius.whattocookwith.ui.screen.pantry

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import diomaxius.whattocookwith.ui.components.ingredientcard.EditablePantry
import diomaxius.whattocookwith.ui.components.ingredientcard.IngredientCardForPantry
import diomaxius.whattocookwith.ui.components.ingredientcard.Pantry

@Composable
fun PantryScreen(
    modifier: Modifier,
    viewModel: PantryScreenViewModel = hiltViewModel(),
) {
    val pantry by viewModel.ingredients.collectAsState()

    Surface(
        color = MaterialTheme.colorScheme.surface
    ) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp)
                .padding(top = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(pantry, key = { it.name }) { ingredient ->
                var editablePantry by rememberSaveable { mutableStateOf(false) }

                if (!editablePantry) {
                    IngredientCardForPantry(
                        ingredient = ingredient,
                        onLongClick = {
                            editablePantry = !editablePantry
                        }

                    ) {
                        Pantry(
                            ingredient = it
                        )
                    }
                } else {
                    IngredientCardForPantry(
                        ingredient = ingredient,
                        onLongClick = {
                            editablePantry = !editablePantry
                        }
                    ) {
                        EditablePantry(
                            ingredient = it,
                            increaseQuantity = viewModel::increaseIngredientQuantity,
                            decreaseQuantity = viewModel::decreaseIngredientQuantity
                        )
                    }
                }
            }
        }
    }
}