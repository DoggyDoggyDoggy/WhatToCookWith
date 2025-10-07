package diomaxius.whattocookwith.ui.screen.pantry

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@Composable
fun PantryBottomBar(
    state: ScreenState,
    setState: (ScreenState) -> Unit,
) {
    val screens = ScreenState.entries.toTypedArray()
    val selectedIndex = state.ordinal

    SecondaryTabRow(
        modifier = Modifier.navigationBarsPadding(),
        selectedTabIndex = selectedIndex,
        indicator = {
            Box(
                modifier = Modifier
                    .tabIndicatorOffset(selectedIndex)
                    .padding(horizontal = 12.dp)
                    .fillMaxSize()
                    .clip(RoundedCornerShape(32.dp))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.tertiaryContainer)
                )
            }
        },
        divider = {}
    ) {
        screens.forEachIndexed { index, screen ->
            val selected = selectedIndex == index
            Tab(
                modifier = Modifier.tabModifier(selected),
                selected = selected,
                onClick = { setState(screen) },
                text = {
                    Text(
                        text = screen.title,
                        color = if (selected) MaterialTheme.colorScheme.onTertiaryContainer
                        else MaterialTheme.colorScheme.tertiary
                    )
                }
            )
        }
    }
}

@Composable
fun Modifier.tabModifier(selected: Boolean): Modifier {
    return if (selected) {
        this
            .zIndex(1f)
            .padding(horizontal = 12.dp)
            .clip(RoundedCornerShape(64.dp))
    } else {
        this
            .zIndex(1f)
            .padding(horizontal = 12.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.tertiary,
                shape = RoundedCornerShape(64.dp)
            )
            .clip(RoundedCornerShape(64.dp))
    }
}