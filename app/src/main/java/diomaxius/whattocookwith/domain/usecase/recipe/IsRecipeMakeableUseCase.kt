package diomaxius.whattocookwith.domain.usecase.recipe

import diomaxius.whattocookwith.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsRecipeMakeableUseCase @Inject constructor(
    private val repository: RecipeRepository
) {
    operator fun invoke(recipeId: Long): Flow<Boolean> =
        repository.isRecipeMakeable(recipeId)
}