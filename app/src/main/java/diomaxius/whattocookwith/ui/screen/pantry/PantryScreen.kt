package diomaxius.whattocookwith.ui.screen.pantry

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import diomaxius.whattocookwith.domain.model.Ingredient
import diomaxius.whattocookwith.navigation.LocalNavController
import diomaxius.whattocookwith.ui.components.PopBackArrowButton
import diomaxius.whattocookwith.ui.components.TopBar
import diomaxius.whattocookwith.ui.components.ingredientcard.ingredientcardforpantry.EditablePantry
import diomaxius.whattocookwith.ui.components.ingredientcard.ingredientcardforpantry.IngredientCardForPantry
import diomaxius.whattocookwith.ui.components.ingredientcard.ingredientcardforpantry.Pantry

@Composable
fun PantryScreen(
    viewModel: PantryScreenViewModel = hiltViewModel(),
) {
    val pantry by viewModel.ingredients.collectAsState()
    val query by viewModel.query.collectAsState()

    val navHostController = LocalNavController.current

    Scaffold(
        topBar = {
            TopBar(
                text = "What to cook with",
                navigationButton = {
                    PopBackArrowButton {
                        navHostController.popBackStack()
                    }
                }
            )
        }
    ) { innerPadding ->
        Content(
            modifier = Modifier.padding(innerPadding),
            pantry = pantry,
            query = query,
            increaseIngredientQuantity = viewModel::increaseIngredientQuantity,
            decreaseIngredientQuantity = viewModel::decreaseIngredientQuantity,
            setQuery = viewModel::setQuery
        )
    }
}

@Composable
fun Content(
    modifier: Modifier,
    pantry: List<Ingredient>,
    query: String,
    increaseIngredientQuantity: (Ingredient) -> Unit,
    decreaseIngredientQuantity: (Ingredient) -> Unit,
    setQuery: (String) -> Unit,
) {
    Surface(
        color = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp)
                .padding(top = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = query,
                onValueChange = {setQuery(it)},
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(pantry, key = { it.name }) { ingredient ->
                    var editablePantry by rememberSaveable { mutableStateOf(false) }

                    if (!editablePantry) {
                        IngredientCardForPantry(
                            ingredient = ingredient,
                            onLongClick = {
                                editablePantry = !editablePantry
                            }

                        ) {
                            Pantry(
                                ingredient = it
                            )
                        }
                    } else {
                        IngredientCardForPantry(
                            ingredient = ingredient,
                            onLongClick = {
                                editablePantry = !editablePantry
                            }
                        ) {
                            EditablePantry(
                                ingredient = it,
                                increaseQuantity = increaseIngredientQuantity,
                                decreaseQuantity = decreaseIngredientQuantity
                            )
                        }
                    }
                }
            }
        }
    }
}