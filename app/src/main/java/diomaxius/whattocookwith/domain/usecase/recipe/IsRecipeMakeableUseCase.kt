package diomaxius.whattocookwith.domain.usecase.recipe

import diomaxius.whattocookwith.domain.repository.RecipeRepository
import javax.inject.Inject

class IsRecipeMakeableUseCase @Inject constructor(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(recipeId: Long): Boolean =
        repository.isRecipeMakeable(recipeId)
}