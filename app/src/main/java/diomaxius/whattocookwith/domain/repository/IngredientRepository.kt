package diomaxius.whattocookwith.domain.repository

import diomaxius.whattocookwith.domain.model.Ingredient
import diomaxius.whattocookwith.domain.model.RecipeIngredient
import kotlinx.coroutines.flow.Flow

interface IngredientRepository {
    suspend fun insertIngredient(ingredient: Ingredient)
    fun getIngredients(minQuantity: Int): Flow<List<Ingredient>>
    fun searchByName(pattern: String, minQuantity: Int): Flow<List<Ingredient>>
    suspend fun deleteIngredient(ingredient: Ingredient)
    suspend fun editIngredient(ingredient: Ingredient)
    suspend fun editIngredient(oldIngredient: Ingredient, newIngredient: Ingredient)
    suspend fun consumeIngredients(ingredients: List<RecipeIngredient>)
}