package ar.edu.unlam.mobile.scaffolding.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ar.edu.unlam.mobile.scaffolding.ui.screens.PetViewData

interface Selectable {
    val isSelected: Boolean
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
fun InteractiveList(
    items: List<PetViewData>,
    onClick: (PetViewData) -> Unit,
    onLongClick: () -> Unit,
    showAction: Boolean = false,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        items(items.size) {
            items[it].let {
                Row(
                    modifier =
                        Modifier
                            .combinedClickable(
                                onClick = {
                                    onClick(it)
                                },
                                onLongClick = {
                                    onLongClick()
                                },
                            ),
                ) {
                    // Tilde loca
                    AnimatedVisibility(
                        visible = showAction,
                        modifier = Modifier.align(Alignment.CenterVertically),
                    ) {
                        SelectCircle(
                            isSelected =
                                it.selected.value,
                            onClick = {
                                onClick(it)
                            },
                        )
                    }
                    PetCard(pet = it.pet)
                }
            }
        }
    }
}
