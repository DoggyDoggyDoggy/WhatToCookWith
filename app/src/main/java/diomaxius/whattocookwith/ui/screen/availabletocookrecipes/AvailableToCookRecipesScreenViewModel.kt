package diomaxius.whattocookwith.ui.screen.availabletocookrecipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import diomaxius.whattocookwith.domain.model.Recipe
import diomaxius.whattocookwith.domain.usecase.recipe.GetMakeableRecipesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class AvailableToCookRecipesScreenViewModel @Inject constructor(
    getMakeableRecipesUseCase: GetMakeableRecipesUseCase
) : ViewModel() {
    val recipes: StateFlow<List<Recipe>> =
        getMakeableRecipesUseCase()
        .distinctUntilChanged()
        .flowOn(Dispatchers.IO)
        .stateIn(scope = viewModelScope, started = SharingStarted.WhileSubscribed(5000), initialValue = emptyList())
}