package diomaxius.whattocookwith.ui.screen.recipe

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import diomaxius.whattocookwith.domain.model.Recipe
import diomaxius.whattocookwith.navigation.LocalNavController
import diomaxius.whattocookwith.ui.components.CustomButton
import diomaxius.whattocookwith.ui.components.PopBackArrowButton
import diomaxius.whattocookwith.ui.components.TopBar
import kotlinx.coroutines.launch

@Composable
fun RecipeScreen(
    viewModel: RecipeScreenViewModel = hiltViewModel(),
) {
    val recipe by viewModel.recipe.collectAsState()
    val isRecipeMakeable by viewModel.isRecipeMakeable.collectAsState()

    val navHostController = LocalNavController.current

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState) { data ->
                Snackbar(
                    snackbarData = data,
                    shape = RoundedCornerShape(32.dp),
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
        },
        topBar = {
            TopBar(
                text = recipe.name,
                navigationButton = { PopBackArrowButton(navHostController) }
            )
        }
    ) { innerPadding ->
        Content(
            modifier = Modifier.padding(innerPadding),
            recipe = recipe,
            isRecipeMakeable = isRecipeMakeable,
            startCooking = viewModel::startCooking,
            showSnackbar = { message ->
                scope.launch {
                    snackbarHostState.showSnackbar(message)
                }
            }
        )
    }
}

@Composable
fun Content(
    modifier: Modifier = Modifier,
    recipe: Recipe,
    isRecipeMakeable: Boolean, //It became unnecessary after refactoring. Keep for now just in case
    startCooking: () -> Unit,
    showSnackbar: (String) -> Unit,
) {
    var isCooking by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Spacer(modifier = Modifier)

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
                IngredientCard(
                    name = ingredient.ingredientName,
                    quantity = ingredient.requiredQuantity.toString(),
                    unit = ingredient.unit
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        if(!isCooking){
            CustomButton(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = {
                    startCooking()
                    showSnackbar("Ingredients have been subtracted from your pantry")
                    isCooking = true
                },
                text = "Start cooking"
            )
        } else {
            CookingCard(
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(bottom = 12.dp)
            )
        }
    }
}

@Composable
fun CookingCard(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .width(220.dp)
            .height(48.dp),
        shape = RoundedCornerShape(64.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Text(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            text = "Enjoy your meal!",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp
        )
    }
}