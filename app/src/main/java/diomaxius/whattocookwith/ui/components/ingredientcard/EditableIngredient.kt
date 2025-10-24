package diomaxius.whattocookwith.ui.components.ingredientcard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import diomaxius.whattocookwith.domain.model.Ingredient
import diomaxius.whattocookwith.ui.components.ingredientdialog.EditIngredientDialog
/*
You can safely delete an ingredient from the list of ingredients,
but if this ingredient is on the user's Panrty list,
a confirmation window will be displayed to confirm the deletion.
*/

//There is currently a bug that will crash the application if this ingredient is used in any recipe.

//It's worth moving EditableIngredient out of this package and into the corresponding screen.
//As I was developing it, I didn't know if it would be used in just one screen or not.
@Composable
fun EditableIngredient(
    ingredient: Ingredient,
    deleteIngredient: (Ingredient) -> Unit,
    editIngredient: (Ingredient, Ingredient) -> Unit,
) {
    var showEditDialog by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }

    Icon(
        modifier = Modifier
            .size(42.dp)
            .clickable {
                showEditDialog = true
            },
        imageVector = Icons.Default.Edit,
        contentDescription = "Edit ingredient"
    )

    Icon(
        modifier = Modifier
            .size(42.dp)
            .clickable {
                if (ingredient.quantity > 0) showDeleteDialog = true
                else deleteIngredient(ingredient)
            },
        imageVector = Icons.Default.Delete,
        tint = Color(0xFFE36363),
        contentDescription = "Delete ingredient"
    )

    if (showEditDialog) {
        EditIngredientDialog(
            dialogTitle = "Edit ingredient",
            ingredient = ingredient,
            closeDialog = { showEditDialog = false },
            saveIngredient = editIngredient
        )
    }

    if (showDeleteDialog) {
        IngredientDeleteDialog(
            ingredient = ingredient,
            onClose = { showDeleteDialog = false },
            onDelete = { deleteIngredient(ingredient) }
        )
    }
}

@Composable
fun IngredientDeleteDialog(
    onClose: () -> Unit,
    onDelete: () -> Unit,
    ingredient: Ingredient,
) {
    AlertDialog(
        title = {
            Text(text = "Delete ingredient")
        },
        text = {
            Text(
                text = "You currently have ${ingredient.quantity} ${ingredient.unit} of ${ingredient.name} in your pantry.",
                fontSize = 18.sp
            )
        },
        onDismissRequest = onClose,
        confirmButton = {
            TextButton(
                onClick = {
                    onDelete()
                    onClose()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE36363)
                )
            ) {
                Text("Delete")
            }
        },
        dismissButton = {
            TextButton(
                onClick = onClose
            ) {
                Text("Close")
            }
        }
    )
}