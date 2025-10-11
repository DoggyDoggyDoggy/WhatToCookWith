package diomaxius.whattocookwith.data.repository

import diomaxius.whattocookwith.data.dao.RecipeDao
import diomaxius.whattocookwith.domain.model.Recipe
import diomaxius.whattocookwith.domain.repository.RecipeRepository
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val recipeDao: RecipeDao
) : RecipeRepository {
    override suspend fun insertFullRecipe(recipe: Recipe) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllRecipesWithIngredients(): List<Recipe> {
        TODO("Not yet implemented")
    }
}