package ar.edu.unlam.mobile.scaffolding.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PetDetailScreen(petId: Int, viewModel: PetDetailViewModel = hiltViewModel()) {
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
    val rationsConsumedPercentage = (foodConsumed / recommendedRations) * 100

    // Estado local para el TextField de distancia
    var distanceInput by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalles de mascota n°$petId") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text(text = "Hola, $petName!", style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(16.dp))

            // Mostrar raciones recomendadas
            Text(text = "Raciones recomendadas: $recommendedRations")

            Spacer(modifier = Modifier.height(16.dp))

            // Barra de progreso para raciones consumidas
            Text(text = "Raciones consumidas: $foodConsumed / $recommendedRations")
            LinearProgressIndicator(
                progress = { foodConsumed / recommendedRations },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp),
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Comida consumida: $foodConsumed")
                Button(
                    onClick = {
                        if (foodConsumed < recommendedRations) {
                            val food = 1
                            viewModel.updateFoodConsumed(food)
                        }
                    },
                    enabled = foodConsumed < recommendedRations, // Deshabilitar si se alcanzó el límite
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Text(text = "Agregar ración")
                }
            }

            // Botón para agregar agua consumida

            Text(text = "Raciones consumidas: $waterConsumed / $recommendedWaterRations")
            LinearProgressIndicator(
                progress = { waterConsumed / recommendedWaterRations },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp),
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Registros de agua")
                Button(
                    onClick = {
                        if (waterConsumed < recommendedRations) {
                            val water = 1
                            viewModel.updateWaterConsumed(water)
                        }
                    },
                    enabled = waterConsumed < recommendedRations,
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Text(text = "Agregar")
                }
            }

            // Distancia recorrida de paseo
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextField(
                    value = distanceInput,
                    onValueChange = { distanceInput = it },
                    label = { Text("Distancia de paseo (metros)") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f)
                )
                Button(
                    onClick = {
                        val distance = distanceInput.toFloatOrNull() ?: 0f
                        viewModel.updateDistanceWalked(distance)
                        distanceInput = "" // Limpiar el input después de actualizar
                    },
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Text(text = "+")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Mostrar la distancia total recorrida
            Text(text = "Distancia total recorrida: $distanceWalked metros")

            Spacer(modifier = Modifier.height(16.dp))

            Divider()

            Spacer(modifier = Modifier.height(16.dp))

            /* Mostrar valores
            Text(text = "Raciones consumidas: $foodConsumed")
            Text(text = "Raciones de agua: $waterConsumed")
            Text(text = "Distancia recorrida: $distanceWalked")
            */

        }
    }
}