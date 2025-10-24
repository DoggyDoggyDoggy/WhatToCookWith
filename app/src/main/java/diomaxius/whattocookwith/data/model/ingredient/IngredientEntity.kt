package diomaxius.whattocookwith.data.model.ingredient

import androidx.room.Entity
import androidx.room.PrimaryKey

//I mentioned a bug in DAO that I just found.
//Probably I will switch to id:Long as a primary key.
@Entity
data class IngredientEntity(
    @PrimaryKey val name: String,
    val quantity: Int,
    val unit: String
)