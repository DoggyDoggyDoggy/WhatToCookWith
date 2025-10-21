package diomaxius.whattocookwith.ui.screen.recipe

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import diomaxius.whattocookwith.domain.model.Recipe
import diomaxius.whattocookwith.domain.usecase.recipe.GetRecipeWithIngredientsUseCase
import diomaxius.whattocookwith.domain.usecase.recipe.IsRecipeMakeableUseCase
import diomaxius.whattocookwith.domain.usecase.recipe.StartCookingRecipeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeScreenViewModel @Inject constructor(
    private val getRecipe: GetRecipeWithIngredientsUseCase,
    private val isRecipeMakeableUseCase: IsRecipeMakeableUseCase,
    private val startCookingRecipeUseCase: StartCookingRecipeUseCase,
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

    private val _isRecipeMakeable = MutableStateFlow(false)
    val isRecipeMakeable = _isRecipeMakeable.asStateFlow()

    init {
        loadRecipe()
        loadIsRecipeMakeable()
    }

    fun startCooking() = viewModelScope.launch {
        startCookingRecipeUseCase(_recipe.value.ingredients)
    }

    private fun loadIsRecipeMakeable() = viewModelScope.launch {
        _isRecipeMakeable.value = isRecipeMakeableUseCase(recipeId.toLong())
    }

    private fun loadRecipe() = viewModelScope.launch {
        _recipe.value = getRecipe(recipeId.toLong())
    }
}