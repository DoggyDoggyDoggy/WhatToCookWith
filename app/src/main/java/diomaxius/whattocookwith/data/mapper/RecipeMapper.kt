package diomaxius.whattocookwith.data.mapper

import diomaxius.whattocookwith.data.model.RecipeEntity
import diomaxius.whattocookwith.domain.model.Recipe

fun RecipeEntity.toDomain(): Recipe = Recipe(
    id = id,
    name = name,
    instructions = instructions
)

fun Recipe.toEntity(): RecipeEntity = RecipeEntity(
    id = id,
    name = name,
    instructions = instructions
)