package diomaxius.whattocookwith.domain.usecase.recipe

import diomaxius.whattocookwith.domain.model.RecipeIngredient
import diomaxius.whattocookwith.domain.repository.IngredientRepository
import javax.inject.Inject

class StartCookingRecipeUseCase @Inject constructor(
    private val ingredientRepository: IngredientRepository
) {
    suspend operator fun invoke(ingredients: List<RecipeIngredient>) =
        ingredientRepository.consumeIngredients(ingredients)
}