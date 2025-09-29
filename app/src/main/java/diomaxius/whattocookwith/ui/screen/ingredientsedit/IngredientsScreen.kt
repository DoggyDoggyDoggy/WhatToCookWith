package diomaxius.whattocookwith.ui.screen.ingredientsedit

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun IngredientsScreen(
    viewModel: IngredientsEditScreenViewModel = hiltViewModel()
) {
    val ingredients by viewModel.ingredients.collectAsState()

    LazyColumn {
        items(ingredients) {
            Text(it.name)
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}