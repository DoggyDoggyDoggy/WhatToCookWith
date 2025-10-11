package diomaxius.whattocookwith.data.mapper.recipe

import diomaxius.whattocookwith.data.model.recipe.RecipeIngredientEntity
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