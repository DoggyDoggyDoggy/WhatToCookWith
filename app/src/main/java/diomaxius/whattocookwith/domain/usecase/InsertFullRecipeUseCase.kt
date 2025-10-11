package diomaxius.whattocookwith.domain.usecase

import diomaxius.whattocookwith.domain.model.Recipe
import diomaxius.whattocookwith.domain.repository.RecipeRepository
import javax.inject.Inject

class InsertFullRecipeUseCase @Inject constructor(
    private val repository: RecipeRepository,
) {
    suspend operator fun invoke(recipe: Recipe) =
        repository.insertFullRecipe(recipe)
}