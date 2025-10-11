package diomaxius.whattocookwith.domain.usecase.ingredient

import diomaxius.whattocookwith.domain.model.Ingredient
import diomaxius.whattocookwith.domain.repository.IngredientRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllIngredientsFromTableUseCase @Inject constructor(
    private val repository: IngredientRepository,
) {
    operator fun invoke(query: String = "", minQuantity: Int = 0): Flow<List<Ingredient>> =
        if (query.trim().isEmpty()) repository.getIngredients(minQuantity)
        else repository.searchByName(pattern = "%$query%", minQuantity)
}