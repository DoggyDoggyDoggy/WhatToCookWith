package diomaxius.whattocookwith.domain.usecase.ingredient

import diomaxius.whattocookwith.domain.model.Ingredient
import diomaxius.whattocookwith.domain.repository.IngredientRepository
import javax.inject.Inject

class DecreaseIngredientQuantityUseCase @Inject constructor(
    private val ingredientRepository: IngredientRepository,
) {
    suspend operator fun invoke(ingredient: Ingredient) {
        val step = when (ingredient.unit) {
            "pcs" -> 1
            "ml" -> 250
            "g" -> 50
            else -> 0
        }

        val newQty = if ((ingredient.quantity - step) > 0) (ingredient.quantity - step) else 0
        ingredientRepository.editIngredient(ingredient.copy(quantity = newQty))
    }
}