package diomaxius.whattocookwith.ui.components.navigationdrawer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.DrawerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import diomaxius.whattocookwith.navigation.NavScreen
import kotlinx.coroutines.launch

@Composable
fun NavigationDrawer(
    modifier: Modifier = Modifier,
    drawerState: DrawerState,
    navHostController: NavHostController,
    content: @Composable () -> Unit,
) {
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        modifier = modifier,
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(
                navHostController = navHostController,
                closeDrawer = {
                    scope.launch {
                        drawerState.close()
                    }
                }
            )
        },
        content = content,
    )
}

@Composable
fun DrawerContent(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    closeDrawer: () -> Unit
) {
    ModalDrawerSheet(
        modifier = modifier.width(200.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            NavigationDrawerItem(
                label = {},
                badge = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Close"
                    )
                },
                selected = false,
                onClick = closeDrawer
            )

            Text(
                text = "Add custom",
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center
            )

            HorizontalDivider(
                color = MaterialTheme.colorScheme.tertiary
            )

            NavigationDrawerItem(
                label = {
                    Text(
                        text = "Ingredients",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                },
                selected = false,
                colors = NavigationDrawerItemDefaults.colors(
                    unselectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                ),
                onClick = {
                    navHostController.navigate(NavScreen.Ingredients.route) {
                        launchSingleTop = true
                    }
                }
            )

            NavigationDrawerItem(
                label = {
                    Text(
                        text = "Recipes",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                },
                selected = false,
                colors = NavigationDrawerItemDefaults.colors(
                    unselectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                ),
                onClick = {
                    navHostController.navigate(NavScreen.AllRecipes.route) {
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}