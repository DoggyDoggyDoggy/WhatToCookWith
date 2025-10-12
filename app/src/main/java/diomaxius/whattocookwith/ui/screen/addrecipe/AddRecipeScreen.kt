package diomaxius.whattocookwith.ui.screen.addrecipe

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
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
        )
    }
}

@Composable
fun Content(
    modifier: Modifier,
    recipeName: String,
    recipeInstructions: String,
    recipeIngredients: List<RecipeIngredient>,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        OutlinedTextField(
            value = recipeName,
            onValueChange = { },
            label = { }
        )

        OutlinedTextField(
            value = recipeInstructions,
            onValueChange = { },
            label = { }
        )
    }
}