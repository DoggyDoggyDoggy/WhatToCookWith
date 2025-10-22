package diomaxius.whattocookwith.domain.repository

import diomaxius.whattocookwith.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    suspend fun insertFullRecipe(recipe: Recipe)
    suspend fun getAllRecipesWithIngredients(): List<Recipe>
    suspend fun getRecipeWithIngredients(id: Long) : Recipe
    fun isRecipeMakeable(recipeId: Long): Flow<Boolean>
    fun getMakeableRecipesWithIngredients(): Flow<List<Recipe>>
}