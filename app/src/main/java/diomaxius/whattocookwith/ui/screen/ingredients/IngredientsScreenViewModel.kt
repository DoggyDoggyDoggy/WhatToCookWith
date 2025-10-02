package diomaxius.whattocookwith.ui.screen.ingredients

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import diomaxius.whattocookwith.domain.model.Ingredient
import diomaxius.whattocookwith.domain.usecase.DeleteIngredientFromTableUseCase
import diomaxius.whattocookwith.domain.usecase.EditIngredientUseCase
import diomaxius.whattocookwith.domain.usecase.GetAllIngredientsFromTableUseCase
import diomaxius.whattocookwith.domain.usecase.InsertIngredientToTableUseCase
import jakarta.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class IngredientsScreenViewModel @Inject constructor(
    getAllIngredientsFromTableUseCase: GetAllIngredientsFromTableUseCase,
    private val insertIngredientToTableUseCase: InsertIngredientToTableUseCase,
    private val deleteIngredientFromTableUseCase: DeleteIngredientFromTableUseCase,
    private val editIngredientUseCase: EditIngredientUseCase,
) : ViewModel() {

    val ingredients: StateFlow<List<Ingredient>> = getAllIngredientsFromTableUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )


    fun saveIngredient() = viewModelScope.launch {
        insertIngredientToTableUseCase(
            Ingredient(
                name = "",
                quantity = 0,
                unit = ""
            )
        )
    }

    fun deleteIngredient(ingredient: Ingredient) = viewModelScope.launch {
        deleteIngredientFromTableUseCase(ingredient)
    }

    fun editIngredient(oldIngredient: Ingredient, newIngredient: Ingredient) =
        viewModelScope.launch {
            Log.i("IngredientsScreenViewModel", "editIngredient: $newIngredient")
            editIngredientUseCase(oldIngredient, newIngredient)
        }
}