package diomaxius.whattocookwith.ui.screen.addrecipe

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import diomaxius.whattocookwith.domain.model.Ingredient
import diomaxius.whattocookwith.domain.model.RecipeIngredient
import diomaxius.whattocookwith.navigation.LocalNavController
import diomaxius.whattocookwith.ui.components.PopBackArrowButton
import diomaxius.whattocookwith.ui.components.TopBar
import diomaxius.whattocookwith.ui.components.ingredientcard.IngredientCard
import diomaxius.whattocookwith.ui.screen.addrecipe.components.AllIngredientDialog
import diomaxius.whattocookwith.ui.screen.addrecipe.components.RecipeInstructions
import diomaxius.whattocookwith.ui.screen.addrecipe.components.RecipeName
import diomaxius.whattocookwith.ui.screen.addrecipe.components.recipeingredients.RecipeIngredientRow

const val RECIPE_NAME_PLACEHOLDER = "For example: Pancakes"
const val RECIPE_Instructions_PLACEHOLDER = """For example:
1. In a bowl, whisk together the egg, sugar, and salt.
2. Add the milk and melted butter, and mix well.
3. Sift in the flour and baking powder, stirring until the batter is smooth and slightly thick (a bit thicker than crepe batter).
4. Heat a non-stick pan over medium heat.
5. Pour about ¼ cup of batter for each pancake. Cook until bubbles appear on the surface, then flip and cook the other side until golden brown.
6. Serve warm with honey, syrup, berries, or banana
"""

@Composable
fun AddRecipeScreen(
    viewModel: AddRecipeScreenViewModel = hiltViewModel(),
) {
    val recipeName by viewModel.recipeName.collectAsState()
    val recipeInstructions by viewModel.recipeInstructions.collectAsState()
    val recipeIngredients by viewModel.recipeIngredients.collectAsState()

    val allIngredients by viewModel.allIngredients.collectAsState()

    val navHostController = LocalNavController.current

    Scaffold(
        topBar = {
            TopBar(
                text = "Add recipe",
                navigationButton = { PopBackArrowButton(navHostController) }
            )
        }
    ) { innerPadding ->
        Content(
            modifier = Modifier.padding(innerPadding),
            recipeName = recipeName,
            recipeInstructions = recipeInstructions,
            recipeIngredients = recipeIngredients,
            allIngredients = allIngredients,
            onRecipeNameChange = viewModel::onRecipeNameChange,
            onRecipeInstructionsChange = viewModel::onRecipeInstructionsChange,
            addRecipeIngredient = viewModel::addRecipeIngredient
        )
    }
}

@Composable
fun Content(
    modifier: Modifier,
    recipeName: String,
    recipeInstructions: String,
    recipeIngredients: List<RecipeIngredient>,
    allIngredients: List<Ingredient>,
    onRecipeNameChange: (String) -> Unit,
    onRecipeInstructionsChange: (String) -> Unit,
    addRecipeIngredient: (Ingredient) -> Unit,
) {
    var showAllIngredientsDialog by rememberSaveable { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Spacer(modifier = Modifier)

        RecipeName(
            recipeName = recipeName,
            onRecipeNameChange = onRecipeNameChange
        )

        RecipeInstructions(
            recipeInstructions = recipeInstructions,
            onRecipeInstructionsChange = onRecipeInstructionsChange
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            RecipeIngredientRow(
                onClick = { showAllIngredientsDialog = true }
            )

            recipeIngredients.forEach {
                IngredientCard(
                    ingredient = Ingredient(
                        name = it.ingredientName,
                        quantity = it.requiredQuantity.toInt(), // Maybe refactor Ingredient model quantity to Int data type
                        unit = it.unit
                    ),
                    actions = {}
                )
            }
        }

        if (showAllIngredientsDialog) {
            AllIngredientDialog(
                onCloseDialog = { showAllIngredientsDialog = false },
                allIngredients = allIngredients,
                focusManager = focusManager,
                addRecipeIngredient = addRecipeIngredient
            )
        }
    }
}