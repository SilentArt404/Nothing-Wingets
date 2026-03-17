package com.nothing.wingets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nothing.wingets.core.theme.NothingWingetsTheme
import com.nothing.wingets.widgets.add.AddWidgetScreen

enum class WidgetType(val title: String, val preview: String) {
    CLOCK("Clock", "12:45"),
    BATTERY("Battery", "82%")
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NothingWingetsTheme {
                WingetsApp()
            }
        }
    }
}

@Composable
private fun WingetsApp() {
    val showAdd = remember { mutableStateOf(false) }
    if (showAdd.value) {
        AddWidgetScreen(onBack = { showAdd.value = false })
    } else {
        WidgetCatalogScreen(onAddWidget = { showAdd.value = true })
    }
}

@Composable
private fun WidgetCatalogScreen(onAddWidget: () -> Unit) {
    val widgets = listOf(WidgetType.CLOCK, WidgetType.BATTERY)
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(text = "Nothing Widget Catalog", style = MaterialTheme.typography.headlineSmall)
                Text(text = "Preview and choose a widget type", style = MaterialTheme.typography.bodyMedium)
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(widgets) { type ->
                WidgetPreviewCard(type = type)
            }
            item {
                Button(onClick = onAddWidget, modifier = Modifier.fillMaxWidth()) {
                    Text("Add Widget")
                }
            }
        }
    }
}

@Composable
private fun WidgetPreviewCard(type: WidgetType) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(text = type.title, style = MaterialTheme.typography.titleLarge)
            Text(text = "Preview", style = MaterialTheme.typography.labelLarge)
            Text(text = type.preview, style = MaterialTheme.typography.displaySmall)
        }
    }
}
