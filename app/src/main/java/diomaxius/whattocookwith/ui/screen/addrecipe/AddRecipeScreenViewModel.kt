package diomaxius.whattocookwith.ui.screen.addrecipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import diomaxius.whattocookwith.domain.model.Ingredient
import diomaxius.whattocookwith.domain.model.RecipeIngredient
import diomaxius.whattocookwith.domain.usecase.ingredient.GetAllIngredientsFromTableUseCase
import diomaxius.whattocookwith.domain.usecase.recipe.InsertFullRecipeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import kotlin.collections.emptyList

@HiltViewModel
class AddRecipeScreenViewModel @Inject constructor(
    getAllIngredientsFromTableUseCase: GetAllIngredientsFromTableUseCase,
    private val insertFullRecipeUseCase: InsertFullRecipeUseCase,
) : ViewModel() {
    val allIngredients: StateFlow<List<Ingredient>> =
        getAllIngredientsFromTableUseCase().stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    private val _recipeName = MutableStateFlow("")
    val recipeName: StateFlow<String> = _recipeName.asStateFlow()

    private val _recipeInstructions = MutableStateFlow("")
    val recipeInstructions: StateFlow<String> = _recipeInstructions.asStateFlow()

    private val _recipeIngredients = MutableStateFlow<List<RecipeIngredient>>(emptyList())
    val recipeIngredients: StateFlow<List<RecipeIngredient>> = _recipeIngredients.asStateFlow()

    fun onRecipeNameChange(name: String) {
        _recipeName.value = name
    }

    fun onRecipeInstructionsChange(instructions: String) {
        _recipeInstructions.value = instructions
    }

    fun onRecipeIngredientChange(ingredient: RecipeIngredient) {
        val ingredients = _recipeIngredients.value.toMutableList()
        ingredients.add(ingredient)
        _recipeIngredients.value = ingredients
    }
}