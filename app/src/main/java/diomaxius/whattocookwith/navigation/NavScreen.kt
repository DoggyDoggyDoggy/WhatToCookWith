package diomaxius.whattocookwith.navigation

sealed class NavScreen (val route: String) {
    object Home: NavScreen("home")
    object Ingredients: NavScreen("ingredients")
    object Pantry: NavScreen("pantry")
    object AddRecipe: NavScreen("addrecipe")
    object AllRecipes: NavScreen("allrecipes")
    object Recipe: NavScreen("recipe/{id}") {
        fun createRoute(id: Long) = "recipe/$id"
    }
}