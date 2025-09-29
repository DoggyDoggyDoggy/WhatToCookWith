package diomaxius.whattocookwith.ui.screen.ingredientsedit

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import diomaxius.whattocookwith.domain.model.Ingredient

@Composable
fun IngredientsScreen(
    viewModel: IngredientsEditScreenViewModel = hiltViewModel(),
) {
    val ingredients by viewModel.ingredients.collectAsState()
    val ingredient by viewModel.ingredient.collectAsState()
    val unit by viewModel.unit.collectAsState()

    var openDialog by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { openDialog = true }
            ) {}
        }
    ) { innerPadding ->
        Content(
            modifier = Modifier.padding(innerPadding),
            ingredients = ingredients,
            ingredient = ingredient,
            unit = unit,
            openDialog = openDialog,
            closeDialog = { openDialog = false },
            onIngredientChange = viewModel::onIngredientChange,
            onUnitChange = viewModel::onUnitChange,
            saveIngredient = viewModel::saveIngredient
        )
    }
}

@Composable
fun Content(
    modifier: Modifier,
    ingredients: List<Ingredient>,
    ingredient: String,
    unit: String,
    openDialog: Boolean,
    closeDialog: () -> Unit,
    onIngredientChange: (String) -> Unit,
    onUnitChange: (String) -> Unit,
    saveIngredient: () -> Unit,
) {
    var ingredientEmptyTextField by remember { mutableStateOf(false) }
    var unitEmptyTextField by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
    ) {
        items(ingredients) {
            Text(it.name)
            Spacer(modifier = Modifier.height(12.dp))
        }
    }

    if (openDialog) {
        AlertDialog(
            icon = {},
            title = {},
            text = {
                IngredientEditScreen(
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
}