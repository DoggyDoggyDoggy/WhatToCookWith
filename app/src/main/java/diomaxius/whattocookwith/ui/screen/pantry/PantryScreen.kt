package diomaxius.whattocookwith.ui.screen.pantry

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import diomaxius.whattocookwith.ui.components.IngredientCard
import diomaxius.whattocookwith.ui.components.PantryList

@Composable
fun PantryScreen(
    modifier: Modifier,
    viewModel: PantryScreenViewModel = hiltViewModel()
) {
    val pantry by viewModel.ingredients.collectAsState()

    Surface(
        color = MaterialTheme.colorScheme.surface
    ) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
        ) {
            item {
                Spacer(
                    modifier = Modifier.height(8.dp)
                )
            }
            items(pantry) {ingredient ->
                IngredientCard(
                    ingredient = ingredient,
                    actions = {
                        PantryList(
                            ingredient = it,
                            increaseQuantity = viewModel::increaseIngredientQuantity,
                        )
                    }
                )
            }
        }
    }
}