package diomaxius.whattocookwith.ui.screen.ingredientsedit.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun AddIngredientDialog(
    ingredient: String,
    unit: String,
    closeDialog: () -> Unit,
    onIngredientChange: (String) -> Unit,
    onUnitChange: (String) -> Unit,
    saveIngredient: () -> Unit,
) {
    var ingredientEmptyTextField by remember { mutableStateOf(false) }
    var unitEmptyTextField by remember { mutableStateOf(false) }

    AlertDialog(
        icon = {},
        title = {},
        text = {
            AddIngredientScreen(
                ingredient = ingredient,
                unit = unit,
                ingredientEmptyTextField = ingredientEmptyTextField,
                unitEmptyTextField = unitEmptyTextField,
                onIngredientChange = onIngredientChange,
                onUnitChange = onUnitChange,
                ingredientEmptyTextFieldSetFalse = { ingredientEmptyTextField = false },
                unitEmptyTextFieldSetFalse = { unitEmptyTextField = false }
            )
        },
        onDismissRequest = {
            onIngredientChange("")
            onUnitChange("")
            ingredientEmptyTextField = false
            unitEmptyTextField = false
            closeDialog()
        },
        confirmButton = {
            Button(
                onClick = {
                    if (ingredient != "" && unit != "") {
                        saveIngredient()
                        onIngredientChange("")
                        onUnitChange("")
                        closeDialog()
                    }
                    else {
                        if (ingredient == "") ingredientEmptyTextField = true
                        if (unit == "") unitEmptyTextField = true
                    }
                }

            ) {
                Text(text = "Save")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onIngredientChange("")
                    onUnitChange("")
                    ingredientEmptyTextField = false
                    unitEmptyTextField = false
                    closeDialog()
                }
            ) {
                Text("Close")
            }
        }
    )
}