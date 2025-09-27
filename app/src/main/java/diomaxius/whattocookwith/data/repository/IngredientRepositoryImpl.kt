package diomaxius.whattocookwith.data.repository

import diomaxius.whattocookwith.data.dao.IngredientDao
import diomaxius.whattocookwith.data.mapper.toDomain
import diomaxius.whattocookwith.data.mapper.toEntity
import diomaxius.whattocookwith.domain.repository.IngredientRepository
import diomaxius.whattocookwith.domain.model.Ingredient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class IngredientRepositoryImpl @Inject constructor(
    private val ingredientDao: IngredientDao
): IngredientRepository {
    override suspend fun insertIngredient(ingredient: Ingredient) =
        ingredientDao.insertIngredient(ingredient.toEntity())

    override fun getIngredients(): Flow<List<Ingredient>> =
        ingredientDao.getIngredients().map { ingredients ->
            ingredients.map { it.toDomain() }
        }

    override suspend fun deleteIngredient(ingredient: Ingredient) =
        ingredientDao.deleteById(ingredient.toEntity())
}