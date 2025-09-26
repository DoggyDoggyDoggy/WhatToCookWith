package diomaxius.whattocookwith.data.mapper

import diomaxius.whattocookwith.data.model.IngredientEntity
import diomaxius.whattocookwith.domain.model.Ingredient

fun IngredientEntity.toDomain(): Ingredient = Ingredient(
    id = id,
    name = name,
    quantity = quantity,
    unit = unit
)

fun Ingredient.toEntity(): IngredientEntity = IngredientEntity(
    id = id,
    name = name,
    quantity = quantity,
    unit = unit
)
