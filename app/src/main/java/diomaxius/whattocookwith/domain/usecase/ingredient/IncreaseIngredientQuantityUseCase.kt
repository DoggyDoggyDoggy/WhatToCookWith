package diomaxius.whattocookwith.domain.usecase.ingredient

import diomaxius.whattocookwith.domain.model.Ingredient
import diomaxius.whattocookwith.domain.repository.IngredientRepository
import javax.inject.Inject

class IncreaseIngredientQuantityUseCase @Inject constructor(
    private val ingredientRepository: IngredientRepository
) {
    suspend operator fun invoke(ingredient: Ingredient) {
        val step = when (ingredient.unit) {
            "pcs" -> 1
            "ml" -> 250
            "g" -> 50
            else -> 0
        }
        ingredientRepository.editIngredient(ingredient.copy(quantity = ingredient.quantity + step))
    }
}