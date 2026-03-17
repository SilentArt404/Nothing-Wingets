package com.nothing.wingets.widgets.add

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private val types = listOf("Clock", "Battery")
private val sizes = listOf("2x1", "2x2", "4x2")

@Composable
fun AddWidgetScreen(onBack: () -> Unit) {
    val selectedType = remember { mutableStateOf(types.first()) }
    val selectedSize = remember { mutableStateOf(sizes.first()) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Добавить виджет", style = MaterialTheme.typography.headlineSmall)
        Text("Выберите тип", style = MaterialTheme.typography.titleMedium)
        types.forEach { type ->
            SelectionCard(
                title = type,
                isSelected = selectedType.value == type,
                onSelect = { selectedType.value = type }
            )
        }
        Text("Выберите размер", style = MaterialTheme.typography.titleMedium)
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.fillMaxWidth()) {
            sizes.forEach { size ->
                OutlinedButton(onClick = { selectedSize.value = size }) {
                    Text(text = size)
                }
            }
        }
        Text("Текущий выбор: ${selectedType.value} ${selectedSize.value}")
        Button(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
            Text("Готово")
        }
    }
}

@Composable
private fun SelectionCard(title: String, isSelected: Boolean, onSelect: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth(), onClick = onSelect) {
        val suffix = if (isSelected) "✓" else ""
        Text(
            text = "$title $suffix",
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
