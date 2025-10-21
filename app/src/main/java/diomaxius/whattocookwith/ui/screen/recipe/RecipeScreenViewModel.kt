package diomaxius.whattocookwith.ui.screen.recipe

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import diomaxius.whattocookwith.domain.model.Recipe
import diomaxius.whattocookwith.domain.usecase.recipe.GetRecipeWithIngredientsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeScreenViewModel @Inject constructor(
    private val getRecipe: GetRecipeWithIngredientsUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val recipeId: String = checkNotNull(savedStateHandle["id"])

    private val _recipe = MutableStateFlow<Recipe>(
        Recipe(
            id = 0,
            name = "",
            instructions = "",
            ingredients = emptyList()
        )
    )
    val recipe = _recipe.asStateFlow()

    init {
        loadRecipe()
    }

    private fun loadRecipe() = viewModelScope.launch {
        _recipe.value = getRecipe(recipeId.toLong())
    }
}