package diomaxius.whattocookwith.ui.screen.ingredients

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import diomaxius.whattocookwith.domain.model.Ingredient
import diomaxius.whattocookwith.navigation.LocalNavController
import diomaxius.whattocookwith.ui.components.ingredientcard.IngredientCard
import diomaxius.whattocookwith.ui.components.ingredientcard.EditableIngredient
import diomaxius.whattocookwith.ui.components.PopBackArrowButton
import diomaxius.whattocookwith.ui.components.TopBar

@Composable
fun IngredientsScreen(
    viewModel: IngredientsScreenViewModel = hiltViewModel(),
) {
    val ingredients by viewModel.ingredients.collectAsState()
    var openDialog by remember { mutableStateOf(false) }
    val navHostController = LocalNavController.current

    Scaffold(
        topBar = {
            TopBar(
                text = "All ingredients available",
                navigationButton = {
                    PopBackArrowButton {
                        navHostController.popBackStack()
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { openDialog = true },
                containerColor = MaterialTheme.colorScheme.tertiaryContainer
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }
        }
    ) { innerPadding ->
        Content(
            modifier = Modifier.padding(innerPadding),
            ingredients = ingredients,
            openDialog = openDialog,
            closeDialog = { openDialog = false },
            saveIngredient = viewModel::saveIngredient,
            deleteIngredient = viewModel::deleteIngredient,
            editIngredient = viewModel::editIngredient
        )
    }
}

@Composable
fun Content(
    modifier: Modifier,
    ingredients: List<Ingredient>,
    openDialog: Boolean,
    closeDialog: () -> Unit,
    saveIngredient: () -> Unit,
    deleteIngredient: (Ingredient) -> Unit,
    editIngredient: (Ingredient, Ingredient) -> Unit,
) {
    Surface(
        color = MaterialTheme.colorScheme.surface
    ) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp)
                .padding(top = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(ingredients, key = { it.name }) { ingredient ->
                IngredientCard(
                    ingredient = ingredient,
                    actions = {
                        EditableIngredient(
                            ingredient = it,
                            deleteIngredient = deleteIngredient,
                            editIngredient = editIngredient,
                        )
                    }
                )
            }
        }
    }

    //if (openDialog) {
    //    IngredientDialog(
    //        ingredient = ingredient,
    //        unit = unit,
    //        closeDialog = closeDialog,
    //        onIngredientChange = onIngredientChange,
    //        onUnitChange = onUnitChange,
    //        saveIngredient = saveIngredient
    //    )
    //}
}