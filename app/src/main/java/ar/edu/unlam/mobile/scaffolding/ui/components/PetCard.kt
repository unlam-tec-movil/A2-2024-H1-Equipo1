package ar.edu.unlam.mobile.scaffolding.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.unlam.mobile.scaffolding.R

@Composable
fun PetCard(
  petName: String,
  petAge: Int,
  modifier: Modifier = Modifier,
  painter: Painter = painterResource(id = R.drawable.paw_round)
) {
  Card(
    modifier = modifier
      .padding(4.dp)
      .fillMaxWidth()
      .height(200.dp)
  ) {
    Row(
      modifier = Modifier.fillMaxSize(),
      horizontalArrangement = Arrangement.SpaceEvenly,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Image(
        painter = painter,
        contentDescription = null
      )
      Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceEvenly
      ) {
        CustomText(
          text = petName,
          fontSize = 32.sp
        )
        CustomText(
          text = stringResource(R.string.pet_age, petAge),
          fontSize = 24.sp
        )
      }
    }
  }
}

@Preview
@Composable
private fun PetCardPreview() {
  PetCard(
    petName = "Mauri",
    petAge = 3
  )
}