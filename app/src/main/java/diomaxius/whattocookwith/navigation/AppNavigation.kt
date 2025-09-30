package diomaxius.whattocookwith.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import diomaxius.whattocookwith.ui.screen.ingredientsedit.IngredientsScreen
import diomaxius.whattocookwith.ui.screen.home.HomeScreen

val LocalNavController = compositionLocalOf<NavHostController> {
    error("NavController not initialized")
}

@Composable
fun AppNavigation(
    navHostController: NavHostController = rememberNavController()
) {
    CompositionLocalProvider(LocalNavController provides navHostController) {
        NavHost(
            navController = navHostController,
            startDestination = NavScreen.Home.route
        ) {
            composable(NavScreen.Home.route) {
                HomeScreen()
            }
            composable(NavScreen.IngredientsEdit.route) {
                IngredientsScreen()
            }
        }
    }
}