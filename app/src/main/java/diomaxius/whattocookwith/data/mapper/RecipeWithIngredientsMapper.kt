package diomaxius.whattocookwith.data.mapper

import diomaxius.whattocookwith.data.model.RecipeWithIngredients
import diomaxius.whattocookwith.domain.model.Recipe

fun RecipeWithIngredients.toDomain(): Recipe = Recipe(
    id = this.recipe.id,
    name = this.recipe.name,
    instructions = this.recipe.instructions,
    ingredients = this.ingredients.map { it.toDomain() }
)