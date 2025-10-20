package diomaxius.whattocookwith.ui.screen.allrecipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import diomaxius.whattocookwith.domain.model.Recipe
import diomaxius.whattocookwith.domain.usecase.recipe.GetAllRecipesWithIngredientsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllRecipesScreenViewModel @Inject constructor(
    private val getAllRecipesWithIngredientsUseCase: GetAllRecipesWithIngredientsUseCase
) : ViewModel() {
    private val _allRecipes = MutableStateFlow<List<Recipe>>(emptyList())
    val allRecipes: StateFlow<List<Recipe>> = _allRecipes.asStateFlow()

    init {
        getAllRecipes()
    }

    private fun getAllRecipes() = viewModelScope.launch {
        _allRecipes.value = getAllRecipesWithIngredientsUseCase()
    }
}
