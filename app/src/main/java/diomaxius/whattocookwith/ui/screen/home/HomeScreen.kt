package diomaxius.whattocookwith.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import diomaxius.whattocookwith.navigation.LocalNavController
import diomaxius.whattocookwith.navigation.NavScreen
import diomaxius.whattocookwith.ui.components.MenuButton
import diomaxius.whattocookwith.ui.components.TopBar
import diomaxius.whattocookwith.ui.components.NavigationDrawer
import kotlinx.coroutines.launch

@Composable
fun HomeScreen() {
    val navHostController = LocalNavController.current

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    NavigationDrawer(
        drawerState = drawerState,
        navHostController = navHostController
    ) {
        Scaffold(
            topBar = {
                TopBar(
                    text = "What to cook with",
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
            .fillMaxSize()
            .padding(horizontal = 12.dp)
            .padding(top = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeroBlock()

        Spacer(modifier = Modifier.height(12.dp))

        CardsRow(
            onFirstCardClick = {
                navHostController.navigate(NavScreen.Pantry.route) {
                    launchSingleTop = true
                }
            },
            onSecondCardClick = {
                navHostController.navigate(NavScreen.AddRecipe.route) {
                    launchSingleTop = true
                }
            }
        )
    }
}