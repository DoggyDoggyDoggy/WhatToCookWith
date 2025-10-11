package diomaxius.whattocookwith.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index

@Entity(
    primaryKeys = ["recipeId", "ingredientName"],
    foreignKeys = [
        ForeignKey(
            RecipeEntity::class,
            parentColumns = ["id"],
            childColumns = ["recipeId"],
            onDelete = CASCADE
        ),
        ForeignKey(
            IngredientEntity::class,
            parentColumns = ["name"],
            childColumns = ["ingredientName"]
        )
    ],
    indices = [Index("ingredientName")]
)
data class RecipeIngredientEntity(
    val recipeId: Long,
    val ingredientName: String,
    val requiredQuantity: Double,
    val unit: String,
    val optional: Boolean = false,
)
