package diomaxius.whattocookwith.ui.screen.pantry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import diomaxius.whattocookwith.domain.model.Ingredient
import diomaxius.whattocookwith.domain.usecase.DecreaseIngredientQuantityUseCase
import diomaxius.whattocookwith.domain.usecase.GetAllIngredientsFromTableUseCase
import diomaxius.whattocookwith.domain.usecase.IncreaseIngredientQuantityUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PantryScreenViewModel @Inject constructor(
    getAllIngredientsFromTableUseCase: GetAllIngredientsFromTableUseCase,
    private val increaseIngredientQuantityUseCase: IncreaseIngredientQuantityUseCase,
    private val decreaseIngredientQuantityUseCase: DecreaseIngredientQuantityUseCase
) : ViewModel(){
    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    private val _screenState = MutableStateFlow(ScreenState.PANTRY)
    val screenState: StateFlow<ScreenState> = _screenState

    val ingredients: StateFlow<List<Ingredient>> =
        combine(
            _query.debounce(300).distinctUntilChanged(),
            _screenState
        ) { q, state -> q to state }
            .flatMapLatest { (q, state) ->
                getAllIngredientsFromTableUseCase(q, state.minQuantity)
            }
            .distinctUntilChanged()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun setScreenState(state: ScreenState) { _screenState.value = state }

    fun setQuery(q: String) { _query.value = q }

    fun increaseIngredientQuantity(ingredient: Ingredient) = viewModelScope.launch {
        increaseIngredientQuantityUseCase(ingredient)
    }

    fun decreaseIngredientQuantity(ingredient: Ingredient) = viewModelScope.launch {
        decreaseIngredientQuantityUseCase(ingredient)
    }
}