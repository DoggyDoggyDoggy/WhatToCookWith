package diomaxius.whattocookwith.ui.screen.allrecipes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import diomaxius.whattocookwith.domain.model.Recipe
import diomaxius.whattocookwith.navigation.LocalNavController
import diomaxius.whattocookwith.navigation.NavScreen
import diomaxius.whattocookwith.ui.components.PopBackArrowButton
import diomaxius.whattocookwith.ui.components.TopBar

@Composable
fun AllRecipesScreen(
    viewModel: AllRecipesScreenViewModel = hiltViewModel(),
) {
    val allRecipes by viewModel.allRecipes.collectAsState()

    val navHostController = LocalNavController.current

    Scaffold(
        topBar = {
            TopBar(
                text = "All recipes",
                navigationButton = { PopBackArrowButton(navHostController) }
            )
        }
    ) { innerPadding ->
        Content(
            modifier = Modifier.padding(innerPadding),
            allRecipes = allRecipes,
            navHostController = navHostController
        )
    }
}

@Composable
fun Content(
    modifier: Modifier,
    allRecipes: List<Recipe>,
    navHostController: NavHostController,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Spacer(modifier = Modifier)
        }
        items(allRecipes) { recipe ->
            RecipeCard(
                recipe = recipe,
                onClick = {
                    navHostController.navigate(NavScreen.Recipe.createRoute(recipe.id)) {
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}