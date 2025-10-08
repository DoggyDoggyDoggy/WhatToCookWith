package diomaxius.whattocookwith.ui.screen.pantry.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import diomaxius.whattocookwith.domain.model.Ingredient
import diomaxius.whattocookwith.ui.components.ingredientcard.ingredientcardforpantry.EditablePantry
import diomaxius.whattocookwith.ui.components.ingredientcard.ingredientcardforpantry.IngredientCardForPantry
import diomaxius.whattocookwith.ui.components.ingredientcard.ingredientcardforpantry.Pantry
import diomaxius.whattocookwith.ui.screen.pantry.ScreenState

@Composable
fun IngredientCardForPantryList(
    pantry: List<Ingredient>,
    increaseIngredientQuantity: (Ingredient) -> Unit,
    decreaseIngredientQuantity: (Ingredient) -> Unit,
    screenState: ScreenState,
) {
    var editablePantryName by rememberSaveable { mutableStateOf<String?>(null) }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        item {
            Spacer(modifier = Modifier.height(8.dp))
        }
        items(pantry, key = { it.name }) { ingredient ->
            val isEditable =
                if (screenState == ScreenState.PANTRY) ingredient.name == editablePantryName else true

            IngredientCardForPantry(
                ingredient = ingredient,
                onLongClick = {
                    editablePantryName = if (isEditable) null else ingredient.name
                }

            ) {
                if (!isEditable) {
                    Pantry(
                        ingredient = it
                    )
                } else {
                    EditablePantry(
                        ingredient = it,
                        increaseQuantity = increaseIngredientQuantity,
                        decreaseQuantity = decreaseIngredientQuantity
                    )
                }
            }
        }
    }
}