package diomaxius.whattocookwith.domain.model

data class RecipeIngredient(
    val recipeId: Long,
    val ingredientName: String,
    val requiredQuantity: Int,
    val unit: String,
    val optional: Boolean
)
