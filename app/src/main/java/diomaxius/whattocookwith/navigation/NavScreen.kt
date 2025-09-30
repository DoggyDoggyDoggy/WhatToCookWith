package diomaxius.whattocookwith.navigation

sealed class NavScreen (val route: String) {
    object Home: NavScreen("home")
    object IngredientsEdit: NavScreen("ingredients_edit")
}