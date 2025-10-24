package diomaxius.whattocookwith.domain.usecase.ingredient

import diomaxius.whattocookwith.domain.model.Ingredient
import diomaxius.whattocookwith.domain.repository.IngredientRepository
import javax.inject.Inject
/*
In the future, it would be better to create several presets for step
and make it a separate class than to use a variable in UseCase.
*/
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