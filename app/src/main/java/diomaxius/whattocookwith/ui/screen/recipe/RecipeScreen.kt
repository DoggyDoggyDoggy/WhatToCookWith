package diomaxius.whattocookwith.ui.screen.recipe

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import diomaxius.whattocookwith.domain.model.Recipe
import diomaxius.whattocookwith.navigation.LocalNavController
import diomaxius.whattocookwith.ui.components.PopBackArrowButton
import diomaxius.whattocookwith.ui.components.TopBar

@Composable
fun RecipeScreen(
    viewModel: RecipeScreenViewModel = hiltViewModel(),
) {
    val recipe by viewModel.recipe.collectAsState()

    val navHostController = LocalNavController.current

    Scaffold(
        topBar = {
            TopBar(
                text = recipe.name,
                navigationButton = { PopBackArrowButton(navHostController) }
            )
        }
    ) { innerPadding ->
        Content(
            modifier = Modifier.padding(innerPadding),
            recipe = recipe
        )
    }
}

@Composable
fun Content(
    modifier: Modifier = Modifier,
    recipe: Recipe,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Text(
                modifier = Modifier.padding(12.dp),
                text = recipe.instructions
            )
        }

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            recipe.ingredients.forEach { ingredient ->
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer
                    )
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = ingredient.ingredientName
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        Text(
                            text = ingredient.requiredQuantity.toString()
                        )

                        Text(
                            text = ingredient.unit
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            modifier = Modifier
                .width(320.dp)
                .height(52.dp)
                .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            onClick = { }
        ) {
            Text(
                text = "Start cooking",
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
            )
        }
    }
}