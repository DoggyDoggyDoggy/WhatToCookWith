package diomaxius.whattocookwith.data.model.recipe

import androidx.room.Embedded
import androidx.room.Relation

//Room query result: Recipe + Ingredients
data class RecipeWithIngredients(
    @Embedded val recipe: RecipeEntity,
    @Relation(parentColumn = "id", entityColumn = "recipeId")
    val ingredients: List<RecipeIngredientEntity>
)