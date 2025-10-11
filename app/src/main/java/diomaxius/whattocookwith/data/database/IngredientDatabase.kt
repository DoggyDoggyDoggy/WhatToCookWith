package diomaxius.whattocookwith.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import diomaxius.whattocookwith.data.dao.IngredientDao
import diomaxius.whattocookwith.data.dao.RecipeDao
import diomaxius.whattocookwith.data.model.ingredient.IngredientEntity
import diomaxius.whattocookwith.data.model.recipe.RecipeEntity
import diomaxius.whattocookwith.data.model.recipe.RecipeIngredientEntity

@Database(
    entities = [
        IngredientEntity::class,
        RecipeEntity::class,
        RecipeIngredientEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class IngredientDatabase : RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "ingredients_db"
    }

    abstract fun ingredientDao(): IngredientDao
    abstract fun recipeDao(): RecipeDao
}