package ar.edu.unlam.mobile.scaffolding.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ar.edu.unlam.mobile.scaffolding.R
import ar.edu.unlam.mobile.scaffolding.ui.theme.Purple10Transparencia53
import ar.edu.unlam.mobile.scaffolding.ui.theme.Purple9Transparencia53

@Composable
fun SwitchWithIconExample(checked: Boolean,
                          onCheckedChange: (Boolean) -> Unit) {
    Surface(
        modifier = Modifier
            .padding(4.dp)
            .shadow(elevation = 5.dp, shape = RoundedCornerShape(999.dp)),
        shape = RoundedCornerShape(999.dp)
    ) {
        Switch(checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedTrackColor = Purple10Transparencia53,
                uncheckedTrackColor = Purple9Transparencia53,
                ),
            thumbContent = {
                Box(
                    modifier = Modifier
                        .padding(1.dp)
                        .shadow(elevation = 15.dp, shape = CircleShape)
                        .background(color = Color.White, shape = CircleShape)
                ){
                    Icon(
                        painter = painterResource(id = R.drawable.paw_round),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(1.dp)
                            .background(color = Color.White, shape = RoundedCornerShape(999.dp))
                    )
                }
            }
        )
    }
}

@Composable
fun CustomSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Surface(
        modifier = Modifier
            .clip(CircleShape)
            .background(Purple9Transparencia53)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable { onCheckedChange(!checked) }
                .shadow(ambientColor = Color.DarkGray, elevation = 10.dp, shape = CircleShape)
                .padding(1.dp)
        ) {
            Switch(
                checked = checked,
                onCheckedChange = null,
                colors = SwitchDefaults.colors(
                    checkedTrackColor = Purple9Transparencia53,
                    uncheckedTrackColor = Purple10Transparencia53,
                ),
                modifier = Modifier
                    .padding(0.dp),
                        thumbContent = {
                    Box(
                        modifier = Modifier
                            .padding(1.dp)
                            .shadow(elevation = 15.dp, shape = CircleShape)
                            .background(color = Color.White, shape = CircleShape)
                    ){
                        Icon(
                            painter = painterResource(id = R.drawable.paw_round),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(0.dp)
                                .background(color = Color.White, shape = RoundedCornerShape(999.dp))
                        )
                    }
                }
            )
        }
    }
}

@Preview (showBackground = false)
@Composable
fun CustomSwitchWithState() {
    val (checked, setChecked) = remember { mutableStateOf(false) }
    Column {
        CustomSwitch(
            checked = checked,
            onCheckedChange = setChecked
        )

        SwitchWithIconExample(
            checked = checked,
            onCheckedChange = setChecked
        )
    }
}
