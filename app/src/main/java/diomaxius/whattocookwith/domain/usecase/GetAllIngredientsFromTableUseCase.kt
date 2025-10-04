package diomaxius.whattocookwith.domain.usecase

import diomaxius.whattocookwith.domain.model.Ingredient
import diomaxius.whattocookwith.domain.repository.IngredientRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllIngredientsFromTableUseCase @Inject constructor(
    private val repository: IngredientRepository,
) {
    operator fun invoke(query: String = ""): Flow<List<Ingredient>> =
        if (query.trim().isEmpty()) repository.getIngredients()
        else repository.searchByName("$query%")
}