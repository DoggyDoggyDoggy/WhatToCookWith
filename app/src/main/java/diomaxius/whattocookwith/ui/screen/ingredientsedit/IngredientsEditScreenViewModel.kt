package diomaxius.whattocookwith.ui.screen.ingredientsedit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import diomaxius.whattocookwith.domain.model.Ingredient
import diomaxius.whattocookwith.domain.usecase.DeleteIngredientFromTableUseCase
import diomaxius.whattocookwith.domain.usecase.GetAllIngredientsFromTableUseCase
import diomaxius.whattocookwith.domain.usecase.InsertIngredientToTableUseCase
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class IngredientsEditScreenViewModel @Inject constructor(
    private val insertIngredientToTableUseCase: InsertIngredientToTableUseCase,
    getAllIngredientsFromTableUseCase: GetAllIngredientsFromTableUseCase,
    private val deleteIngredientFromTableUseCase: DeleteIngredientFromTableUseCase
) : ViewModel() {

    val ingredients: StateFlow<List<Ingredient>> = getAllIngredientsFromTableUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    private val _ingredient = MutableStateFlow<String>("")
    val ingredient = _ingredient

    private val _unit = MutableStateFlow<String>("")
    val unit = _unit

    fun onIngredientChange(ingredient: String) {
        _ingredient.value = ingredient
    }

    fun onUnitChange(unit: String) {
        _unit.value = unit
    }

    fun saveIngredient() = viewModelScope.launch {
        insertIngredientToTableUseCase(
            Ingredient(
                name = _ingredient.value,
                quantity = 0,
                unit = _unit.value
            )
        )
    }
}