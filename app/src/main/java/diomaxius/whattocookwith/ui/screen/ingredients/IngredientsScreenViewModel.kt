package diomaxius.whattocookwith.ui.screen.ingredients

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import diomaxius.whattocookwith.domain.model.Ingredient
import diomaxius.whattocookwith.domain.usecase.ingredient.DeleteIngredientFromTableUseCase
import diomaxius.whattocookwith.domain.usecase.ingredient.EditIngredientUseCase
import diomaxius.whattocookwith.domain.usecase.ingredient.GetAllIngredientsFromTableUseCase
import diomaxius.whattocookwith.domain.usecase.ingredient.InsertIngredientToTableUseCase
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class IngredientsScreenViewModel @Inject constructor(
    getAllIngredientsFromTableUseCase: GetAllIngredientsFromTableUseCase,
    private val insertIngredientToTableUseCase: InsertIngredientToTableUseCase,
    private val deleteIngredientFromTableUseCase: DeleteIngredientFromTableUseCase,
    private val editIngredientUseCase: EditIngredientUseCase,
) : ViewModel() {
    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    val ingredients: StateFlow<List<Ingredient>> = _query
        .debounce(300)
        .distinctUntilChanged()
        .flatMapLatest { q ->
            getAllIngredientsFromTableUseCase(q)
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun setQuery(q: String) { _query.value = q }

    fun saveIngredient(ingredient: Ingredient) = viewModelScope.launch {
        insertIngredientToTableUseCase(ingredient)
    }

    fun deleteIngredient(ingredient: Ingredient) = viewModelScope.launch {
        deleteIngredientFromTableUseCase(ingredient)
    }

    fun editIngredient(oldIngredient: Ingredient, newIngredient: Ingredient) =
        viewModelScope.launch {
            editIngredientUseCase(oldIngredient, newIngredient)
        }
}