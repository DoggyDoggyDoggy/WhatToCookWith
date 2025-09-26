package diomaxius.whattocookwith.domain.model

data class Ingredient(
    val id: Int,
    val name: String,
    val quantity: Int,
    val unit: String
)