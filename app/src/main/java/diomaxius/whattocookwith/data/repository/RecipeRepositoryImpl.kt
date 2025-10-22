package diomaxius.whattocookwith.data.repository

import diomaxius.whattocookwith.data.dao.RecipeDao
import diomaxius.whattocookwith.data.mapper.recipe.toDomain
import diomaxius.whattocookwith.data.mapper.recipe.toEntity
import diomaxius.whattocookwith.domain.model.Recipe
import diomaxius.whattocookwith.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val recipeDao: RecipeDao,
) : RecipeRepository {
    override suspend fun insertFullRecipe(recipe: Recipe) {
        recipeDao.insertFullRecipe(recipe.toEntity(), recipe.ingredients.map { it.toEntity()})
    }

    override suspend fun getAllRecipesWithIngredients(): List<Recipe> =
        recipeDao.getAllRecipesWithIngredients().map { it.toDomain() }

    override suspend fun getRecipeWithIngredients(id: Long): Recipe =
       recipeDao.getRecipeWithIngredients(id).toDomain()

    override fun isRecipeMakeable(recipeId: Long): Flow<Boolean> =
        recipeDao.isRecipeMakeable(recipeId)

    override fun getMakeableRecipesWithIngredients(): Flow<List<Recipe>> =
        recipeDao.getMakeableRecipesWithIngredients().map { it.map { recipe -> recipe.toDomain() } }
}