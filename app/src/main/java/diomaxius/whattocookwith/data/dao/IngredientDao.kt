package diomaxius.whattocookwith.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import diomaxius.whattocookwith.data.model.ingredient.IngredientEntity
import kotlinx.coroutines.flow.Flow

/*
I use name as the Primary Key. Therefore, I overloaded the editIngredient function so
that I can change the ingredient name.

I also just discovered a bug/error. I can't edit an ingredient if it's used in the recipe table.
The app crashes.

So I'll probably switch to a regular id:Int/Long as the Primary Key.
*/
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

    //Not save. Check if enough ingredients before call it
    @Query("UPDATE IngredientEntity SET quantity = quantity - :amount WHERE name = :name")
    suspend fun decreaseQuantity(name: String, amount: Int)
}