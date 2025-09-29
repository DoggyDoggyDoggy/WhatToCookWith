package diomaxius.whattocookwith.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class IngredientEntity(
    @PrimaryKey val name: String,
    val quantity: Int,
    val unit: String
)