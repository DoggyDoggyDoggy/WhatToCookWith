package diomaxius.whattocookwith.ui.screen.pantry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import diomaxius.whattocookwith.domain.model.Ingredient
import diomaxius.whattocookwith.domain.usecase.DecreaseIngredientQuantityUseCase
import diomaxius.whattocookwith.domain.usecase.GetAllIngredientsFromTableUseCase
import diomaxius.whattocookwith.domain.usecase.IncreaseIngredientQuantityUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PantryScreenViewModel @Inject constructor(
    getAllIngredientsFromTableUseCase: GetAllIngredientsFromTableUseCase,
    private val increaseIngredientQuantityUseCase: IncreaseIngredientQuantityUseCase,
    private val decreaseIngredientQuantityUseCase: DecreaseIngredientQuantityUseCase
) : ViewModel(){
    val ingredients: StateFlow<List<Ingredient>> = getAllIngredientsFromTableUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun increaseIngredientQuantity(ingredient: Ingredient) = viewModelScope.launch {
        increaseIngredientQuantityUseCase(ingredient)
    }

    fun decreaseIngredientQuantity(ingredient: Ingredient) = viewModelScope.launch {
        decreaseIngredientQuantityUseCase(ingredient)
    }
}