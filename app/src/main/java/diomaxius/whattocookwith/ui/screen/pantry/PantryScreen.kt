package diomaxius.whattocookwith.ui.screen.pantry

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
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

    val navHostController = LocalNavController.current
    val focusManager = LocalFocusManager.current

    var state by rememberSaveable { mutableIntStateOf(0) }
    val titles = listOf("My pantry", "All ingredients")

    Scaffold(
        topBar = {
            TopBar(
                text = "What to cook with",
                navigationButton = {
                    PopBackArrowButton(navHostController)
                }
            )
        },
        bottomBar = {
            SecondaryTabRow(
                modifier = Modifier.navigationBarsPadding(),
                selectedTabIndex = state,
                indicator = {
                    Box(
                        modifier = Modifier
                            .tabIndicatorOffset(state)
                            .padding(horizontal = 12.dp)
                            .fillMaxSize()
                            .clip(RoundedCornerShape(32.dp))
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(MaterialTheme.colorScheme.tertiaryContainer)
                        )
                    }
                },
                divider = {}
            ) {
                titles.forEachIndexed { index, title ->
                    val tabModifier = if (state == index) {
                        Modifier.zIndex(1f)
                            .padding(horizontal = 12.dp)
                            .clip(RoundedCornerShape(32.dp))
                    } else {
                        Modifier.zIndex(1f)
                            .padding(horizontal = 12.dp)
                            .border(
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.tertiary,
                                shape = RoundedCornerShape(32.dp)
                            )
                    }
                    Tab(
                        modifier = tabModifier,
                        selected = state == index,
                        onClick = { state = index },
                        text = {
                            Text(
                                text = title,
                                color = if (state == index) MaterialTheme.colorScheme.onTertiaryContainer
                                else MaterialTheme.colorScheme.tertiary
                            )
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        Content(
            modifier = Modifier.padding(innerPadding),
            pantry = pantry,
            query = query,
            increaseIngredientQuantity = viewModel::increaseIngredientQuantity,
            decreaseIngredientQuantity = viewModel::decreaseIngredientQuantity,
            setQuery = viewModel::setQuery,
            focusManager = focusManager
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

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                }
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