package diomaxius.whattocookwith.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import diomaxius.whattocookwith.data.dao.IngredientDao
import diomaxius.whattocookwith.data.model.IngredientEntity

@Database(
    entities = [IngredientEntity::class],
    version = 1,
    exportSchema = false
)
abstract class IngredientDatabase : RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "ingredients_db"
    }
    abstract fun ingredientDao(): IngredientDao
}