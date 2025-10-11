package diomaxius.whattocookwith.data.mapper

import diomaxius.whattocookwith.data.model.RecipeEntity
import diomaxius.whattocookwith.domain.model.Recipe

fun Recipe.toEntity(): RecipeEntity = RecipeEntity(
    id = id,
    name = name,
    instructions = instructions
)