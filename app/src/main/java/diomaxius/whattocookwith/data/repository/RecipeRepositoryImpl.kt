package diomaxius.whattocookwith.data.repository

import diomaxius.whattocookwith.data.dao.RecipeDao
import diomaxius.whattocookwith.data.mapper.toEntity
import diomaxius.whattocookwith.data.mapper.toDomain
import diomaxius.whattocookwith.domain.model.Recipe
import diomaxius.whattocookwith.domain.repository.RecipeRepository
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val recipeDao: RecipeDao,
) : RecipeRepository {
    override suspend fun insertFullRecipe(recipe: Recipe) {
        recipeDao.insertFullRecipe(recipe.toEntity(), recipe.ingredients.map { it.toEntity() })
    }

    override suspend fun getAllRecipesWithIngredients(): List<Recipe> =
        recipeDao.getAllRecipesWithIngredients().map { it.toDomain() }
}