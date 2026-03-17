package com.nothingwingets.widgets.clockdate.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nothingwingets.widgets.clockdate.data.ClockDateState
import com.nothingwingets.widgets.core.WidgetSize

@Composable
fun ClockDatePreview(
    state: ClockDateState,
    size: WidgetSize,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.padding(12.dp)) {
        Text(text = "Clock & date", style = MaterialTheme.typography.titleMedium)
        Text(text = "Size: ${size.name}", style = MaterialTheme.typography.bodySmall)
        Text(text = state.toUiSummary(), style = MaterialTheme.typography.bodyMedium)
    }
}
