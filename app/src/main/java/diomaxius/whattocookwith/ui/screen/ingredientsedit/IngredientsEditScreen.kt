package diomaxius.whattocookwith.ui.screen.ingredientsedit

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import diomaxius.whattocookwith.ui.components.DropdownMenu

@Composable
fun IngredientsEditScreen(
    viewModel: IngredientsEditScreenViewModel = hiltViewModel(),
) {
    val ingredient by viewModel.ingredient.collectAsState()
    val unit by viewModel.unit.collectAsState()

    var ingredientEmptyTextField by remember { mutableStateOf(false) }
    var unitEmptyTextField by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .animateContentSize(animationSpec = tween(durationMillis = 300)),
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
                fontSize = 22.sp,
                color = Color.Red
            )

            Spacer(modifier = Modifier.height(32.dp))
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            OutlinedTextField(
                value = ingredient,
                isError = ingredientEmptyTextField,
                onValueChange = {
                    viewModel.onIngredientChange(it)
                    if (it != "") ingredientEmptyTextField = false
                },
                label = { Text("Ingredient") },
                placeholder = { Text("Egg") }
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Choose unit:",
                    textAlign = TextAlign.Start
                )

                Spacer(modifier = Modifier.width(12.dp))

                DropdownMenu(
                    selected = unit,
                    isError = unitEmptyTextField,
                    selection = listOf("pcs", "ml", "g"),
                    onUnitSelected = { unit ->
                        viewModel.onUnitChange(unit)
                    },
                    toggleErrorFalse = { unitEmptyTextField = false }
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                if (ingredient != "" && unit != "") viewModel.saveIngredient()
                else {
                    if (ingredient == "") ingredientEmptyTextField = true
                    if (unit == "") unitEmptyTextField = true
                }
            }

        ) {
            Text(text = "Save")
        }
    }
}