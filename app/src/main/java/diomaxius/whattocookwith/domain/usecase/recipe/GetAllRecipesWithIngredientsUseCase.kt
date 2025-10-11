package diomaxius.whattocookwith.domain.usecase.recipe

import diomaxius.whattocookwith.domain.model.Recipe
import diomaxius.whattocookwith.domain.repository.RecipeRepository
import javax.inject.Inject

class GetAllRecipesWithIngredientsUseCase @Inject constructor(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(): List<Recipe> =
        repository.getAllRecipesWithIngredients()
}