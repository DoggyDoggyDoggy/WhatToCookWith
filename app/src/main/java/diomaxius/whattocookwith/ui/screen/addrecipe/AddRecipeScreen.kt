package diomaxius.whattocookwith.ui.screen.addrecipe

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun AddRecipeScreen(
    viewModel: AddRecipeScreenViewModel = hiltViewModel()
) {
    Text("Add recipe")
}