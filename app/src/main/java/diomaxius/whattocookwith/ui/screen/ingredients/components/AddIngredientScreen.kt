package diomaxius.whattocookwith.ui.screen.ingredients.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import diomaxius.whattocookwith.ui.components.DropdownMenu

@Composable
fun AddIngredientScreen(
    ingredient: String,
    unit: String,
    ingredientEmptyTextField: Boolean,
    unitEmptyTextField: Boolean,
    onIngredientChange: (String) -> Unit,
    onUnitChange: (String) -> Unit,
    ingredientEmptyTextFieldSetFalse: () -> Unit,
    unitEmptyTextFieldSetFalse: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            visible = ingredientEmptyTextField || unitEmptyTextField,
            enter = expandVertically(animationSpec = tween(300)) + fadeIn(animationSpec = tween(300)),
            exit = shrinkVertically(animationSpec = tween(300)) + fadeOut(animationSpec = tween(200))
        ) {
            Text(
                text = "Please fill all the fields",
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                color = Color.Red
            )

            Spacer(modifier = Modifier.height(22.dp))
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            OutlinedTextField(
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    errorContainerColor = MaterialTheme.colorScheme.surface
                ),
                value = ingredient,
                isError = ingredientEmptyTextField,
                onValueChange = {
                    onIngredientChange(it)
                    if (it != "") ingredientEmptyTextFieldSetFalse()
                },
                label = { Text("Ingredient") },
                placeholder = { Text("Egg") }
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Choose unit:",
                    textAlign = TextAlign.Start,
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.width(12.dp))

                DropdownMenu(
                    selected = unit,
                    isError = unitEmptyTextField,
                    selection = listOf("pcs", "ml", "g"),
                    onUnitSelected = { unit ->
                        onUnitChange(unit)
                    },
                    toggleErrorFalse = { unitEmptyTextFieldSetFalse() }
                )
            }
        }
    }
}