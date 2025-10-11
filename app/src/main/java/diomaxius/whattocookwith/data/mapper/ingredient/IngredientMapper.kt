package diomaxius.whattocookwith.data.mapper.ingredient

import diomaxius.whattocookwith.data.model.ingredient.IngredientEntity
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