package diomaxius.whattocookwith.ui.screen.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import diomaxius.whattocookwith.ui.components.topbar.MenuButton
import diomaxius.whattocookwith.ui.components.topbar.TopBar

@Composable
fun HomeScreen() {
    Scaffold (
        topBar = {
            TopBar(
                text = "What to cook with",
                navigationButton = {
                    MenuButton({})
                }
            )
        }
    ) { innerPadding ->
        Content(
            modifier= Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun Content(modifier: Modifier = Modifier) {

}