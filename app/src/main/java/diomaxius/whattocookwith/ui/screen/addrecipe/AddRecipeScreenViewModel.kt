package diomaxius.whattocookwith.ui.screen.addrecipe

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import diomaxius.whattocookwith.domain.usecase.ingredient.GetAllIngredientsFromTableUseCase
import diomaxius.whattocookwith.domain.usecase.recipe.InsertFullRecipeUseCase
import javax.inject.Inject

@HiltViewModel
class AddRecipeScreenViewModel @Inject constructor(
    private val insertFullRecipeUseCase: InsertFullRecipeUseCase,
    private val getAllIngredientsFromTableUseCase: GetAllIngredientsFromTableUseCase,
) : ViewModel() {

}