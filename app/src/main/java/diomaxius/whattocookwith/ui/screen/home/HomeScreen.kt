package diomaxius.whattocookwith.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import diomaxius.whattocookwith.navigation.LocalNavController
import diomaxius.whattocookwith.navigation.NavScreen
import diomaxius.whattocookwith.ui.components.MenuButton
import diomaxius.whattocookwith.ui.components.TopBar
import diomaxius.whattocookwith.ui.components.navigationdrawer.NavigationDrawer
import kotlinx.coroutines.launch

@Composable
fun HomeScreen() {
    val navHostController = LocalNavController.current

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    NavigationDrawer (
        drawerState = drawerState,
        navHostController = navHostController
    ) {
        Scaffold(
            topBar = {
                TopBar(
                    text = "",
                    navigationButton = {
                        MenuButton {
                            scope.launch {
                                drawerState.open()
                            }
                        }
                    }
                )
            }
        ) { innerPadding ->
            Content(
                modifier = Modifier.padding(innerPadding),
                navHostController = navHostController
            )
        }
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
            Text("All recipes")
        }
    }
}