package se.kruskakli.nso.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


enum class TabPage {
    Home, Settings, Packages, Devices, Error
}

@Composable
fun Divider() {
    Spacer(modifier = Modifier
        .height(1.dp)
        .fillMaxWidth()
        .background(Color.LightGray)
    )
}
