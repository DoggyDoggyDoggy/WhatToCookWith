package diomaxius.whattocookwith.ui.screen.addrecipe

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import diomaxius.whattocookwith.domain.model.Ingredient
import diomaxius.whattocookwith.domain.model.RecipeIngredient
import diomaxius.whattocookwith.navigation.LocalNavController
import diomaxius.whattocookwith.ui.components.PopBackArrowButton
import diomaxius.whattocookwith.ui.components.TopBar

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
            onRecipeIngredientChange = viewModel::onRecipeIngredientChange
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
    onRecipeIngredientChange: (RecipeIngredient) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        OutlinedTextField(
            value = recipeName,
            onValueChange = {onRecipeNameChange(it)},
            label = { }
        )

        OutlinedTextField(
            value = recipeInstructions,
            onValueChange = { onRecipeInstructionsChange(it)},
            label = { }
        )
    }
}