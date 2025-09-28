package diomaxius.whattocookwith.ui.screen.ingredientsedit

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import diomaxius.whattocookwith.domain.usecase.DeleteIngredientFromTableUseCase
import diomaxius.whattocookwith.domain.usecase.GetAllIngredientsFromTableUseCase
import diomaxius.whattocookwith.domain.usecase.InsertIngredientToTableUseCase
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow

@HiltViewModel
class IngredientsEditScreenViewModel @Inject constructor(
    private val insertIngredientToTableUseCase: InsertIngredientToTableUseCase,
    private val getAllIngredientsFromTableUseCase: GetAllIngredientsFromTableUseCase,
    private val deleteIngredientFromTableUseCase: DeleteIngredientFromTableUseCase
) : ViewModel() {
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
}