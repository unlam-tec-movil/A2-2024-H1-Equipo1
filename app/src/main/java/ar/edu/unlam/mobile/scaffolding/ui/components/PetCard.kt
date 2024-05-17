package ar.edu.unlam.mobile.scaffolding.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.unlam.mobile.scaffolding.R
import ar.edu.unlam.mobile.scaffolding.ui.theme.Purple6

@Composable
fun PetCard(
    petName: String,
    petAge: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    painter: Painter = painterResource(id = R.drawable.paw_round),
    textColor: Color = Color.White,
    cardColor: Color = Purple6,
    titleTextSize: TextUnit = 36.sp,
    subtitleTextSize: TextUnit = 24.sp,
) {
    Card(
        modifier =
            modifier
                .padding(4.dp)
                .fillMaxWidth()
                .height(120.dp)
                .clickable { onClick() },
        colors =
            CardDefaults.cardColors(
                containerColor = cardColor,
            ),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp, 4.dp)
                    .background(Color.Transparent),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier =
                    Modifier
                        .size(100.dp),
            ) {
                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier =
                        Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .fillMaxSize()
                            .background(Color.White),
                )
            }
            Column(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .background(Color.Transparent),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceEvenly,
            ) {
                CustomText(
                    text = petName,
                    fontSize = titleTextSize,
                    maxLines = 1,
                    color = textColor,
                )
                CustomText(
                    text = stringResource(R.string.pet_age, petAge),
                    fontSize = subtitleTextSize,
                    maxLines = 1,
                    color = textColor,
                )
            }
        }
    }
}

@Preview(
    showSystemUi = true,
)
@Composable
private fun PetCardPreview() {
    PetCard(
        petName = "Mauri",
        petAge = 3,
        painter = painterResource(R.drawable.dog_silhouette_01),
        onClick = {},
    )
}
