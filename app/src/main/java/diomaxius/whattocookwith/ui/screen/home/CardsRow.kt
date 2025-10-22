package diomaxius.whattocookwith.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import diomaxius.whattocookwith.R

@Composable
fun CardsRow(
    modifier: Modifier = Modifier,
    onFirstCardClick: () -> Unit,
    onSecondCardClick: () -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        CustomCard(
            modifier = Modifier
                .weight(0.5f)
                .clickable { onFirstCardClick() },
            cardColor = MaterialTheme.colorScheme.secondaryContainer,
            text = "My pantry",
            image = R.drawable.home_pantry
        )

        CustomCard(
            modifier = Modifier
                .weight(0.5f)
                .clickable { onSecondCardClick() },
            cardColor = MaterialTheme.colorScheme.secondaryContainer,
            text = "All recipes",
            image = R.drawable.home_allrecipes
        )
    }
}

@Composable
fun CustomCard(
    modifier: Modifier,
    cardColor: Color,
    text: String,
    image: Int,
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = cardColor
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = text,
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold
            )

            Image(
                painter = painterResource(image),
                contentScale = ContentScale.Fit,
                contentDescription = ""
            )
        }
    }
}