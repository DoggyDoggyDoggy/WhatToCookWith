package diomaxius.whattocookwith.ui.components.ingredientcard.ingredientcardforpantry

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import diomaxius.whattocookwith.domain.model.Ingredient
import diomaxius.whattocookwith.ui.components.ingredientcard.IngredientCard

@Composable
fun IngredientCardForPantry(
    ingredient: Ingredient,
    onLongClick: () -> Unit,
    modifier: Modifier = Modifier,
    actions: @Composable RowScope.(Ingredient) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    val clickableModifier = modifier.then(
        Modifier.combinedClickable(
            interactionSource = interactionSource,
            indication = LocalIndication.current,
            onClick = {},
            onLongClick = onLongClick,
            role = Role.Button
        )
    )

    IngredientCard(
        ingredient = ingredient,
        modifier = clickableModifier,
        actions = actions
    )
}