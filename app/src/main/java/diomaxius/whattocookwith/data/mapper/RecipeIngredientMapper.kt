package diomaxius.whattocookwith.data.mapper

import diomaxius.whattocookwith.data.model.RecipeIngredientEntity
import diomaxius.whattocookwith.domain.model.RecipeIngredient

fun RecipeIngredientEntity.toDomain(): RecipeIngredient = RecipeIngredient(
    recipeId = recipeId,
    ingredientName = ingredientName,
    requiredQuantity = requiredQuantity,
    unit = unit,
    optional = optional
)

fun RecipeIngredient.toEntity(): RecipeIngredientEntity = RecipeIngredientEntity(
    recipeId = recipeId,
    ingredientName = ingredientName,
    requiredQuantity = requiredQuantity,
    unit = unit,
    optional = optional
)