package diomaxius.whattocookwith.ui.screen.pantry

enum class ScreenState(val title: String, val minQuantity: Int) {
    PANTRY("My pantry", 1),
    INGREDIENTS("All ingredients", 0)
}