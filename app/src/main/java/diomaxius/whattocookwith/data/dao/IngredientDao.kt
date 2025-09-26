package diomaxius.whattocookwith.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import diomaxius.whattocookwith.data.model.IngredientEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface IngredientDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIngredient(ingredient: IngredientEntity)

    @Query("SELECT * FROM IngredientEntity ORDER BY name ASC")
    fun getIngredients(): Flow<List<IngredientEntity>>

    @Delete
    suspend fun deleteById(ingredient: IngredientEntity)
}