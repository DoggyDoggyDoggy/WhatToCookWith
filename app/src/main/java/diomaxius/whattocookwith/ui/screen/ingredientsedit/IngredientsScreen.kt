package diomaxius.whattocookwith.ui.screen.ingredientsedit

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import diomaxius.whattocookwith.domain.model.Ingredient
import diomaxius.whattocookwith.navigation.LocalNavController
import diomaxius.whattocookwith.ui.components.ingredientcard.IngredientCard
import diomaxius.whattocookwith.ui.components.ingredientcard.IngredientCardMode
import diomaxius.whattocookwith.ui.components.topbar.PopBackArrowButton
import diomaxius.whattocookwith.ui.components.topbar.TopBar
import diomaxius.whattocookwith.ui.screen.ingredientsedit.components.AddIngredientDialog

@Composable
fun IngredientsScreen(
    viewModel: IngredientsEditScreenViewModel = hiltViewModel(),
) {
    val ingredients by viewModel.ingredients.collectAsState()
    val ingredient by viewModel.ingredient.collectAsState()
    val unit by viewModel.unit.collectAsState()

    var openDialog by remember { mutableStateOf(false) }

    val navHostController = LocalNavController.current

    Scaffold(
        topBar = {
            TopBar(
                text = "All ingredients available",
                navigationButton = {
                    PopBackArrowButton{
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
            ingredient = ingredient,
            unit = unit,
            openDialog = openDialog,
            closeDialog = { openDialog = false },
            onIngredientChange = viewModel::onIngredientChange,
            onUnitChange = viewModel::onUnitChange,
            saveIngredient = viewModel::saveIngredient,
            deleteIngredient = viewModel::deleteIngredient
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
    deleteIngredient: (Ingredient) -> Unit,
) {
    Surface(
        color = MaterialTheme.colorScheme.surface
    ) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
        ) {
            item {
                Spacer(
                    modifier = Modifier.height(8.dp)
                )
            }
            items(ingredients) {
                IngredientCard(
                    ingredient = it,
                    ingredientCardMode = IngredientCardMode.IngredientList,
                    deleteIngredient = deleteIngredient
                )
            }
        }
    }

    if (openDialog) {
        AddIngredientDialog(
            ingredient = ingredient,
            unit = unit,
            closeDialog = closeDialog,
            onIngredientChange = onIngredientChange,
            onUnitChange = onUnitChange,
            saveIngredient = saveIngredient
        )
    }
}