package diomaxius.whattocookwith.data.repository

import diomaxius.whattocookwith.data.dao.IngredientDao
import diomaxius.whattocookwith.data.mapper.ingredient.toDomain
import diomaxius.whattocookwith.data.mapper.ingredient.toEntity
import diomaxius.whattocookwith.domain.repository.IngredientRepository
import diomaxius.whattocookwith.domain.model.Ingredient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class IngredientRepositoryImpl @Inject constructor(
    private val ingredientDao: IngredientDao,
) : IngredientRepository {
    override suspend fun insertIngredient(ingredient: Ingredient) =
        ingredientDao.insertIngredient(ingredient.toEntity())

    override fun getIngredients(minQuantity: Int): Flow<List<Ingredient>> =
        ingredientDao.getIngredients(minQuantity).map { ingredients ->
            ingredients.map { it.toDomain() }
        }

    override fun searchByName(pattern: String, minQuantity: Int): Flow<List<Ingredient>> =
        ingredientDao.searchByName(pattern, minQuantity).map { ingredients ->
            ingredients.map { it.toDomain() }
        }

    override suspend fun deleteIngredient(ingredient: Ingredient) =
        ingredientDao.deleteById(ingredient.toEntity())

    override suspend fun editIngredient(ingredient: Ingredient) =
        ingredientDao.editIngredient(ingredient.toEntity())

    override suspend fun editIngredient(oldIngredient: Ingredient, newIngredient: Ingredient) =
        ingredientDao.editIngredient(oldIngredient.toEntity(), newIngredient.toEntity())
}