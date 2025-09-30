package diomaxius.whattocookwith.ui.components.ingredientcard

sealed class IngredientCardMode {
    object PantryList : IngredientCardMode()
    object IngredientList : IngredientCardMode()
}