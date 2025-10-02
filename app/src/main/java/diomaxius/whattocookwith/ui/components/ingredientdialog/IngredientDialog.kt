package diomaxius.whattocookwith.ui.components.ingredientdialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import diomaxius.whattocookwith.domain.model.Ingredient

@Composable
fun IngredientDialog(
    ingredient: Ingredient,
    closeDialog: () -> Unit,
    saveIngredient: (Ingredient, Ingredient) -> Unit,
) {
    var ingredientEmptyTextField by remember { mutableStateOf(false) }
    var unitEmptyTextField by remember { mutableStateOf(false) }

    var name by remember { mutableStateOf(ingredient.name) }
    var unit by remember { mutableStateOf(ingredient.unit) }

    val resetAndClose = {
        ingredientEmptyTextField = false
        unitEmptyTextField = false
        closeDialog()
    }

    val saveAndClose = {
        if (name != "" && unit != "") {
            saveIngredient(ingredient, ingredient.copy(name = name, unit = unit))
            closeDialog()
        } else {
            if (name == "") ingredientEmptyTextField = true
            if (unit == "") unitEmptyTextField = true
        }
    }

    AlertDialog(
        containerColor = MaterialTheme.colorScheme.secondaryContainer,
        title = {
            Column {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Add ingredient",
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(8.dp))

                HorizontalDivider(
                    color = MaterialTheme.colorScheme.primary
                )
            }
        },
        text = {
            AddIngredientScreen(
                ingredient = name,
                unit = unit,
                ingredientEmptyTextField = ingredientEmptyTextField,
                unitEmptyTextField = unitEmptyTextField,
                onIngredientChange = { name = it },
                onUnitChange = { unit = it },
                ingredientEmptyTextFieldSetFalse = { ingredientEmptyTextField = false },
                unitEmptyTextFieldSetFalse = { unitEmptyTextField = false }
            )
        },
        onDismissRequest = resetAndClose,
        confirmButton = {
            Button(
                onClick = saveAndClose
            ) {
                Text(text = "Save")
            }
        },
        dismissButton = {
            TextButton(
                onClick = resetAndClose
            ) {
                Text("Close")
            }
        }
    )
}