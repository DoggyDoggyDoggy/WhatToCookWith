package diomaxius.whattocookwith.data.mapper.recipe

import diomaxius.whattocookwith.data.model.recipe.RecipeEntity
import diomaxius.whattocookwith.domain.model.Recipe

fun Recipe.toEntity(): RecipeEntity = RecipeEntity(
    id = id,
    name = name,
    instructions = instructions
)