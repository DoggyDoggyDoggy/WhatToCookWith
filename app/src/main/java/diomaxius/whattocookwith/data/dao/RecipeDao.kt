package diomaxius.whattocookwith.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import diomaxius.whattocookwith.data.model.recipe.RecipeEntity
import diomaxius.whattocookwith.data.model.recipe.RecipeIngredientEntity
import diomaxius.whattocookwith.data.model.recipe.RecipeWithIngredients
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: RecipeEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipeIngredients(items: List<RecipeIngredientEntity>)

    @Update
    suspend fun updateRecipe(recipe: RecipeEntity)

    @Delete
    suspend fun deleteRecipe(recipe: RecipeEntity)

    @Query("DELETE FROM RecipeIngredientEntity WHERE recipeId = :recipeId")
    suspend fun deleteIngredientsByRecipeId(recipeId: Long)

    @Transaction
    @Query("SELECT * FROM RecipeEntity")
    suspend fun getAllRecipesWithIngredients(): List<RecipeWithIngredients>

    @Transaction
    @Query("SELECT * FROM RecipeEntity WHERE id = :id")
    suspend fun getRecipeWithIngredients(id: Long): RecipeWithIngredients

    @Transaction
    suspend fun insertFullRecipe(recipe: RecipeEntity, items: List<RecipeIngredientEntity>) {
        val id = insertRecipe(recipe)
        val prepared = items.map { it.copy(recipeId = id) }
        insertRecipeIngredients(prepared)
    }

    @Transaction
    suspend fun updateFullRecipe(recipe: RecipeEntity, items: List<RecipeIngredientEntity>) {
        updateRecipe(recipe)
        deleteIngredientsByRecipeId(recipe.id)
        val prepared = items.map { it.copy(recipeId = recipe.id) }
        insertRecipeIngredients(prepared)
    }

    @Transaction
    @Query(
        """
        SELECT r.* FROM RecipeEntity r
        LEFT JOIN RecipeIngredientEntity ri ON r.id = ri.recipeId
        LEFT JOIN IngredientEntity p ON p.name = ri.ingredientName
        GROUP BY r.id
        HAVING SUM(
            CASE
                WHEN ri.optional = 1 THEN 0
                WHEN p.name IS NULL THEN 1
                WHEN p.quantity < ri.requiredQuantity THEN 1
                ELSE 0
            END
        ) = 0
        """
    )
    fun getMakeableRecipesWithIngredients(): Flow<List<RecipeWithIngredients>>

    @Query(
        """
        SELECT CASE WHEN COALESCE(SUM(
            CASE
                WHEN ri.optional = 1 THEN 0
                WHEN p.name IS NULL THEN 1
                WHEN p.quantity < ri.requiredQuantity THEN 1
                ELSE 0
            END
        ), 0) = 0 THEN 1 ELSE 0 END
        FROM RecipeEntity r
        LEFT JOIN RecipeIngredientEntity ri ON r.id = ri.recipeId
        LEFT JOIN IngredientEntity p ON p.name = ri.ingredientName
        WHERE r.id = :recipeId
        """
    )
    fun isRecipeMakeable(recipeId: Long): Flow<Boolean>
}