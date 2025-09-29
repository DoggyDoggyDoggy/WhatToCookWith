package diomaxius.whattocookwith.data.mapper

import diomaxius.whattocookwith.data.model.IngredientEntity
import diomaxius.whattocookwith.domain.model.Ingredient

fun IngredientEntity.toDomain(): Ingredient = Ingredient(
    name = name,
    quantity = quantity,
    unit = unit
)

fun Ingredient.toEntity(): IngredientEntity = IngredientEntity(
    name = name,
    quantity = quantity,
    unit = unit
)