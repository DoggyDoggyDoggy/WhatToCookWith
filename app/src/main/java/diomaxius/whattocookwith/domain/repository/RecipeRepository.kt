package diomaxius.whattocookwith.domain.repository

import diomaxius.whattocookwith.domain.model.Recipe

interface RecipeRepository {
    suspend fun insertFullRecipe(recipe: Recipe)
    suspend fun getAllRecipesWithIngredients(): List<Recipe>
    suspend fun getRecipeWithIngredients(id: Long) : Recipe
}