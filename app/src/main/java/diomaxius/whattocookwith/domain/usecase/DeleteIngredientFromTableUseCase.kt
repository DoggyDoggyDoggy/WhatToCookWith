package diomaxius.whattocookwith.domain.usecase

import diomaxius.whattocookwith.domain.model.Ingredient
import diomaxius.whattocookwith.domain.repository.IngredientRepository
import javax.inject.Inject

class DeleteIngredientFromTableUseCase @Inject constructor(
    private val repository: IngredientRepository
) {
    suspend operator fun invoke(ingredient: Ingredient) =
        repository.deleteIngredient(ingredient)
}