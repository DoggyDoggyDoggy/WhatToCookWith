package diomaxius.whattocookwith

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import diomaxius.whattocookwith.data.model.Ingredient
import diomaxius.whattocookwith.ui.components.IngredientCard
import diomaxius.whattocookwith.ui.theme.WhatToCookWithTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            WhatToCookWithTheme {
                IngredientCard(
                    ingredient = Ingredient("Egg", 0)
                )
            }
        }
    }
}