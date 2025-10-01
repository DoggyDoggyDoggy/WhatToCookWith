package diomaxius.whattocookwith.navigation

sealed class NavScreen (val route: String) {
    object Home: NavScreen("home")
    object Ingredients: NavScreen("ingredients_edit")
}