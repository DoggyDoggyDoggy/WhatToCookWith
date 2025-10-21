package diomaxius.whattocookwith.ui.screen.recipe

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun RecipeScreen(
    viewModel: RecipeScreenViewModel = hiltViewModel()
) {
    val recipe by viewModel.recipe.collectAsState()

    Text(recipe.name)

}