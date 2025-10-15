package diomaxius.whattocookwith.ui.screen.addrecipe

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import diomaxius.whattocookwith.domain.model.Ingredient
import diomaxius.whattocookwith.domain.model.RecipeIngredient
import diomaxius.whattocookwith.navigation.LocalNavController
import diomaxius.whattocookwith.ui.components.PopBackArrowButton
import diomaxius.whattocookwith.ui.components.TopBar

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
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp)
            .padding(top = 24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Recipe name:",
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = recipeName,
                onValueChange = { onRecipeNameChange(it) },
                placeholder = { Text(RECIPE_NAME_PLACEHOLDER) }
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Recipe instructions:",
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                minLines = 16,
                value = recipeInstructions,
                onValueChange = { onRecipeInstructionsChange(it) },
                placeholder = { Text(RECIPE_Instructions_PLACEHOLDER) }
            )
        }
    }
}