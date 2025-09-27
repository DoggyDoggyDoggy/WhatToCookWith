package diomaxius.whattocookwith.domain.repository

import diomaxius.whattocookwith.domain.model.Ingredient
import kotlinx.coroutines.flow.Flow

interface IngredientRepository {
    suspend fun insertIngredient(ingredient: Ingredient)
    fun getIngredients(): Flow<List<Ingredient>>
    suspend fun deleteIngredient(ingredient: Ingredient)
}