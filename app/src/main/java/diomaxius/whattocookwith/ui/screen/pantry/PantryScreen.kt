package diomaxius.whattocookwith.ui.screen.pantry

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import diomaxius.whattocookwith.domain.model.Ingredient
import diomaxius.whattocookwith.navigation.LocalNavController
import diomaxius.whattocookwith.ui.components.PopBackArrowButton
import diomaxius.whattocookwith.ui.components.SearchOutlinedTextField
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
    val screenState by viewModel.screenState.collectAsState()

    val navHostController = LocalNavController.current
    val focusManager = LocalFocusManager.current

    Scaffold(
        topBar = {
            TopBar(
                text = if (screenState == ScreenState.PANTRY) ScreenState.PANTRY.title else ScreenState.INGREDIENTS.title,
                navigationButton = {
                    PopBackArrowButton(navHostController)
                }
            )
        },
        bottomBar = {
            PantryBottomBar(
                state = screenState,
                setState = viewModel::setScreenState
            )
        }
    ) { innerPadding ->
        Content(
            modifier = Modifier.padding(innerPadding),
            pantry = pantry,
            query = query,
            increaseIngredientQuantity = viewModel::increaseIngredientQuantity,
            decreaseIngredientQuantity = viewModel::decreaseIngredientQuantity,
            setQuery = viewModel::setQuery,
            focusManager = focusManager,
            state = screenState
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
    focusManager: FocusManager,
    state: ScreenState,
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
            SearchOutlinedTextField(
                query = query,
                onQueryChange = setQuery,
                focusManager = focusManager,
                shape = RoundedCornerShape(32.dp)
            )

            IngredientCardForPantryList(
                pantry = pantry,
                increaseIngredientQuantity = increaseIngredientQuantity,
                decreaseIngredientQuantity = decreaseIngredientQuantity
            )
        }
    }
}

@Composable
fun IngredientCardForPantryList(
    pantry: List<Ingredient>,
    increaseIngredientQuantity: (Ingredient) -> Unit,
    decreaseIngredientQuantity: (Ingredient) -> Unit,
) {
    var editablePantryName by rememberSaveable { mutableStateOf<String?>(null) }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        item {
            Spacer(modifier = Modifier.height(8.dp))
        }
        items(pantry, key = { it.name }) { ingredient ->
            val isEditable = ingredient.name == editablePantryName

            IngredientCardForPantry(
                ingredient = ingredient,
                onLongClick = {
                    editablePantryName = if (isEditable) null else ingredient.name
                }

            ) {
                if (!isEditable) {
                    Pantry(
                        ingredient = it
                    )
                } else {
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