package diomaxius.whattocookwith.domain.model

data class Recipe(
    val id: Long,
    val name: String,
    val instructions: String,
)