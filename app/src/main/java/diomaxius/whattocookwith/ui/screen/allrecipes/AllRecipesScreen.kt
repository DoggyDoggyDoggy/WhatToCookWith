package diomaxius.whattocookwith.ui.screen.allrecipes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import diomaxius.whattocookwith.navigation.LocalNavController
import diomaxius.whattocookwith.ui.components.PopBackArrowButton
import diomaxius.whattocookwith.ui.components.TopBar

@Composable
fun AllRecipesScreen() {
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
            modifier = Modifier.padding(innerPadding)
        )

    }
}

@Composable
fun Content(modifier: Modifier) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text("All recipes")
    }
}