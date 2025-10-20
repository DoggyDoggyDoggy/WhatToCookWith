package diomaxius.whattocookwith.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import diomaxius.whattocookwith.navigation.LocalNavController
import diomaxius.whattocookwith.navigation.NavScreen

@Composable
fun HomeScreen() {
    val navHostController = LocalNavController.current

    Scaffold { innerPadding ->
        Content(
            modifier = Modifier.padding(innerPadding),
            navHostController = navHostController
        )
    }
}

@Composable
fun Content(
    modifier: Modifier,
    navHostController: NavHostController,
) {
    Column(
        modifier = modifier
    ) {
        Button(
            onClick = {
                navHostController.navigate(NavScreen.Ingredients.route) {
                    launchSingleTop = true
                }
            }
        ) {
            Text("Go to ingredients")
        }
        Button(
            onClick = {
                navHostController.navigate(NavScreen.Pantry.route) {
                    launchSingleTop = true
                }
            }
        ) {
            Text("Go to pantry")
        }
        Button(
            onClick = {
                navHostController.navigate(NavScreen.AddRecipe.route) {
                    launchSingleTop = true
                }
            }
        ) {
            Text("Add recipe")
        }
        Button(
            onClick = {
                navHostController.navigate(NavScreen.AllRecipes.route) {
                    launchSingleTop = true
                }
            }
        ) {
            Text("All recipe")
        }
    }
}