package ar.edu.unlam.mobile.scaffolding.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ar.edu.unlam.mobile.scaffolding.ui.components.CustomText
import ar.edu.unlam.mobile.scaffolding.ui.components.PetInfoField
import ar.edu.unlam.mobile.scaffolding.ui.theme.Purple1
import ar.edu.unlam.mobile.scaffolding.ui.theme.Purple8

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PetDetailScreen(
    onSaveButtonClick: () -> Unit,
    petId: Int,
    viewModel: PetDetailViewModel = hiltViewModel(),
) {
    // Cargar los detalles del pet cuando se inicia la pantalla
    LaunchedEffect(petId) {
        viewModel.loadPetDetails(petId)
    }

    // Observar los estados del ViewModel
    val petName by viewModel.petName.collectAsState()
    val foodConsumed by viewModel.foodConsumed.collectAsState()
    val waterConsumed by viewModel.waterConsumed.collectAsState()
    val distanceWalked by viewModel.distanceWalked.collectAsState()
    val recommendedRations by viewModel.recommendedRations.collectAsState()
    val recommendedWaterRations by viewModel.recommendedWaterRations.collectAsState()
    // val rationsConsumedPercentage = (foodConsumed / recommendedRations) * 100

    val foodProgress = if (recommendedRations > 0) foodConsumed / recommendedRations else 0f
    val waterProgress =
        if (recommendedWaterRations > 0) waterConsumed / recommendedWaterRations else 0f

    // Estado local para el TextField de distancia
    var distanceInput by remember { mutableStateOf("") }

    Scaffold(topBar = {
        TopAppBar(title = { CustomText(text = "Hola, $petName!", fontSize = 40.sp) })
    }) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .padding(innerPadding)
                    .padding(16.dp),
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Mostrar raciones recomendadas
            CustomText(
                text = "Raciones recomendadas por día: Comida - $recommendedRations, Agua - $recommendedWaterRations",
                fontSize = 20.sp,
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Barra de progreso para raciones consumidas
            CustomText(
                text = "Raciones consumidas: ${foodConsumed.toInt()} / $recommendedRations",
                fontSize = 20.sp,
            )
            LinearProgressIndicator(
                progress = { foodProgress.coerceIn(0f, 1f) },
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(30.dp),
                color = Purple1,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                CustomText(text = "Raciones de comida: ", fontSize = 20.sp)
                Button(
                    onClick = {
                        if (foodConsumed < recommendedRations) {
                            val food = 1
                            viewModel.updateFoodConsumed(food)
                        }
                    },
                    enabled = foodConsumed < recommendedRations, // Deshabilitar si se alcanzó el límite
                    modifier = Modifier.padding(start = 8.dp),
                    colors =
                        ButtonDefaults.buttonColors(
                            containerColor = Purple8,
                            contentColor = Color.White,
                        ),
                ) {
                    CustomText(text = "Agregar", fontSize = 20.sp)
                }
                Button(
                    onClick = {
                        if (foodConsumed.toInt() == recommendedRations || foodConsumed < recommendedRations) {
                            val food = -1
                            viewModel.updateFoodConsumed(food)
                        }
                    },
                    enabled = foodConsumed <= recommendedRations && foodConsumed.toInt() != 0,
                    modifier =
                        Modifier
                            .padding(start = 8.dp),
                    colors =
                        ButtonDefaults.buttonColors(
                            containerColor = Purple8,
                            contentColor = Color.White,
                        ),
                ) {
                    CustomText(text = "Quitar", fontSize = 20.sp)
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            // AGUA

            CustomText(
                text = "Raciones consumidas: ${waterConsumed.toInt()} / $recommendedWaterRations",
                fontSize = 20.sp,
            )
            LinearProgressIndicator(
                progress = { waterProgress.coerceIn(0f, 1f) },
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(30.dp),
                color = Purple1,
            )

            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                CustomText(text = "Registros de agua:", fontSize = 20.sp)
                Button(
                    onClick = {
                        if (waterConsumed < recommendedWaterRations) {
                            val water = 1
                            viewModel.updateWaterConsumed(water)
                        }
                    },
                    enabled = waterConsumed < recommendedWaterRations,
                    modifier =
                        Modifier
                            .padding(start = 8.dp),
                    colors =
                        ButtonDefaults.buttonColors(
                            containerColor = Purple8,
                            contentColor = Color.White,
                        ),
                ) {
                    CustomText(text = "Agregar", fontSize = 20.sp)
                }

                Button(
                    onClick = {
                        if (waterConsumed.toInt() ==
                            recommendedWaterRations ||
                            waterConsumed < recommendedWaterRations
                        ) {
                            val water = -1
                            viewModel.updateWaterConsumed(water)
                        }
                    },
                    enabled = waterConsumed <= recommendedWaterRations && waterConsumed.toInt() != 0,
                    modifier =
                        Modifier
                            .padding(start = 8.dp),
                    colors =
                        ButtonDefaults.buttonColors(
                            containerColor = Purple8,
                            contentColor = Color.White,
                        ),
                ) {
                    CustomText(text = "Quitar", fontSize = 20.sp)
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            // Distancia recorrida de paseo
            Column(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(vertical = 8.dp),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                PetInfoField(
                    value = distanceInput,
                    onValueChange = { distanceInput = it },
                    title = "Paseo (m)",
                    keyboardType = KeyboardType.Number,
                    modifier =
                        Modifier
                            .wrapContentHeight(),
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    modifier =
                        Modifier
                            .padding(start = 8.dp)
                            .fillMaxWidth(),
                    onClick = {
                        val distance = distanceInput.toFloatOrNull() ?: 0f
                        viewModel.updateDistanceWalked(distance)
                        distanceInput = "" // Limpiar el input después de actualizar
                    },
                    colors =
                        ButtonDefaults.buttonColors(
                            containerColor = Purple8,
                        ),
                ) {
                    CustomText(text = "Registrar paseo", fontSize = 20.sp)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Mostrar la distancia total recorrida
            CustomText(text = "Distancia total recorrida: $distanceWalked metros", fontSize = 25.sp)

            Spacer(modifier = Modifier.height(16.dp))

            HorizontalDivider()

            Spacer(modifier = Modifier.height(16.dp))

            /* Mostrar valores
            Text(text = "Raciones consumidas: $foodConsumed")
            Text(text = "Raciones de agua: $waterConsumed")
            Text(text = "Distancia recorrida: $distanceWalked")
             */

            Button(
                onClick = {
                    viewModel.saveData(petId)
                    onSaveButtonClick()
                },
                modifier = Modifier.width(300.dp),
                colors =
                    ButtonDefaults.buttonColors(
                        containerColor = Purple8,
                    ),
            ) {
                CustomText(
                    text = "Guardar Datos",
                    fontSize = 24.sp,
                    color = Color.White,
                )
            }
        }
    }
}
