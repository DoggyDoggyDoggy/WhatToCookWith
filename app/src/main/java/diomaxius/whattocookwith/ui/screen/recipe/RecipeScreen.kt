package diomaxius.whattocookwith.ui.screen.recipe

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun RecipeScreen(
    viewModel: RecipeScreenViewModel = hiltViewModel()
) {
    Text("Recipe Screen")
}