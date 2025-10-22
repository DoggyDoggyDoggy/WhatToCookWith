package diomaxius.whattocookwith.domain.usecase.recipe

import diomaxius.whattocookwith.domain.model.Recipe
import diomaxius.whattocookwith.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMakeableRecipesUseCase @Inject constructor(
    private val repository: RecipeRepository
) {
    operator fun invoke(): Flow<List<Recipe>> =
        repository.getMakeableRecipesWithIngredients()
}