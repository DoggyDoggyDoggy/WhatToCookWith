package diomaxius.whattocookwith.ui.screen.availabletocookrecipes

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun AvailableToCookRecipesScreen(
    viewModel: AvailableToCookRecipesScreenViewModel = hiltViewModel()
) {
    val recipes by viewModel.recipes.collectAsState()
    LazyColumn {
        items(recipes) {
            Text(text = it.name)
        }
    }
}