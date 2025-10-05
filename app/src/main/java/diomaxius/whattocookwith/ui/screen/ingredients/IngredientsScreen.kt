package diomaxius.whattocookwith.ui.screen.ingredients

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import diomaxius.whattocookwith.domain.model.Ingredient
import diomaxius.whattocookwith.navigation.LocalNavController
import diomaxius.whattocookwith.ui.components.ingredientcard.IngredientCard
import diomaxius.whattocookwith.ui.components.ingredientcard.EditableIngredient
import diomaxius.whattocookwith.ui.components.PopBackArrowButton
import diomaxius.whattocookwith.ui.components.SearchOutlinedTextField
import diomaxius.whattocookwith.ui.components.TopBar
import diomaxius.whattocookwith.ui.components.ingredientdialog.CreateIngredientDialog

@Composable
fun IngredientsScreen(
    viewModel: IngredientsScreenViewModel = hiltViewModel(),
) {
    val ingredients by viewModel.ingredients.collectAsState()
    val query by viewModel.query.collectAsState()

    var showAddDialog by remember { mutableStateOf(false) }

    val navHostController = LocalNavController.current
    val focusManager = LocalFocusManager.current

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
                onClick = { showAddDialog = true },
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
            query = query,
            showAddDialog = showAddDialog,
            closeDialog = { showAddDialog = false },
            saveIngredient = viewModel::saveIngredient,
            deleteIngredient = viewModel::deleteIngredient,
            editIngredient = viewModel::editIngredient,
            focusManager = focusManager,
            setQuery = viewModel::setQuery,
        )
    }
}

@Composable
fun Content(
    modifier: Modifier,
    ingredients: List<Ingredient>,
    showAddDialog: Boolean,
    closeDialog: () -> Unit,
    saveIngredient: (Ingredient) -> Unit,
    deleteIngredient: (Ingredient) -> Unit,
    editIngredient: (Ingredient, Ingredient) -> Unit,
    focusManager: FocusManager,
    query: String,
    setQuery: (String) -> Unit,
) {
    Surface(
        color = MaterialTheme.colorScheme.surface
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp)
                .padding(top = 8.dp)
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(top = (56 + 8).dp)
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

            SearchOutlinedTextField(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .zIndex(1f),
                query = query,
                onQueryChange = setQuery,
                focusManager = focusManager,
                shape = RoundedCornerShape(16.dp)
            )
        }
    }

    if (showAddDialog) {
        CreateIngredientDialog(
            dialogTitle = "Add ingredient",
            closeDialog = closeDialog,
            saveIngredient = saveIngredient
        )
    }
}