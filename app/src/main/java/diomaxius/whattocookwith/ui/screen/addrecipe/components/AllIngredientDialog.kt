package diomaxius.whattocookwith.ui.screen.addrecipe.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import diomaxius.whattocookwith.domain.model.Ingredient
import diomaxius.whattocookwith.domain.model.RecipeIngredient
import diomaxius.whattocookwith.ui.components.SearchOutlinedTextField
import diomaxius.whattocookwith.ui.components.ingredientcard.IngredientCard

@Composable
fun AllIngredientDialog(
    onCloseDialog: () -> Unit,
    allIngredients: List<Ingredient>,
    focusManager: FocusManager,
    addRecipeIngredient: (Ingredient) -> Unit,
    query: String,
    setQuery: (String) -> Unit,
    recipeIngredients: List<RecipeIngredient>
) {
    AlertDialog(
        containerColor = MaterialTheme.colorScheme.surface,
        onDismissRequest = { onCloseDialog() },
        title = {
            Column {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Add ingredient",
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(8.dp))

                HorizontalDivider(
                    color = MaterialTheme.colorScheme.primary
                )
            }
        },
        confirmButton = {
            Button(
                onClick = onCloseDialog
            ) {
                Text("Close")
            }
        },
        text = {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    SearchOutlinedTextField(
                        query = query,
                        onQueryChange = {setQuery(it)},
                        focusManager = focusManager,
                        shape = RoundedCornerShape(16.dp)
                    )
                }
                items(allIngredients, key = { it.name }) { ingredient ->
                    IngredientCard(
                        ingredient = ingredient,
                        actions = {
                            if (ingredient.name !in recipeIngredients.map { it.ingredientName }){
                                IconButton(
                                    colors = IconButtonDefaults.iconButtonColors(
                                        containerColor = MaterialTheme.colorScheme.surfaceContainer
                                    ),
                                    onClick = {
                                        addRecipeIngredient(ingredient)
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Add,
                                        contentDescription = "Add ingredient",
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                }
                            } else {
                                IconButton(
                                    colors = IconButtonDefaults.iconButtonColors(
                                        containerColor = MaterialTheme.colorScheme.tertiary
                                    ),
                                    onClick = {}
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = "Checked ingredient",
                                        tint = MaterialTheme.colorScheme.onTertiary
                                    )
                                }
                            }
                        }
                    )
                }
            }
        }
    )
}