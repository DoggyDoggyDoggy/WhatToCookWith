package diomaxius.whattocookwith.domain.usecase.recipe

import diomaxius.whattocookwith.domain.model.Recipe
import diomaxius.whattocookwith.domain.repository.RecipeRepository
import javax.inject.Inject

class GetRecipeWithIngredientsUseCase @Inject constructor(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(id: Long): Recipe =
        repository.getRecipeWithIngredients(id)
}