package com.nothingwingets.widgets.calendar.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CalendarSettingsScreen() {
    var enabled by remember { mutableStateOf(true) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Calendar settings")
        Switch(
            checked = enabled,
            onCheckedChange = { enabled = it },
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
