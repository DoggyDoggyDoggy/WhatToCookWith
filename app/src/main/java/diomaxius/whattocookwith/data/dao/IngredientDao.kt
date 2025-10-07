package diomaxius.whattocookwith.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import diomaxius.whattocookwith.data.model.IngredientEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface IngredientDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertIngredient(ingredient: IngredientEntity)

    @Query("SELECT * FROM IngredientEntity WHERE quantity >= :minQuantity ORDER BY name ASC")
    fun getIngredients(minQuantity: Int): Flow<List<IngredientEntity>>

    @Query("SELECT * FROM IngredientEntity WHERE quantity >= :minQuantity AND name LIKE :pattern COLLATE NOCASE ORDER BY name ASC")
    fun searchByName(pattern: String, minQuantity: Int): Flow<List<IngredientEntity>>

    @Delete
    suspend fun deleteById(ingredient: IngredientEntity)

    @Update
    suspend fun editIngredient(ingredient: IngredientEntity)

    @Transaction
    suspend fun editIngredient(oldIngredient: IngredientEntity, newIngredient: IngredientEntity) {
        deleteById(oldIngredient)
        insertIngredient(newIngredient)
    }
}