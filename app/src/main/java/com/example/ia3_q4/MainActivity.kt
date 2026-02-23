package com.example.ia3_q4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { ResponsiveScreen() }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResponsiveScreen() {

    val items = listOf(
        "Profile", "Notifications", "Privacy",
        "Appearance", "Storage", "About"
    )

    var selectedIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Responsive Layout") })
        }
    ) { paddingValues ->

        val configuration = LocalConfiguration.current
        val isWide = configuration.screenWidthDp.dp >= 600.dp

        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            if (!isWide) {
                //Phone Layout (Single Column)
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {

                    Text("Options", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(8.dp))

                    LazyColumn(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        itemsIndexed(items) { index, item ->
                            ListItem(
                                headlineContent = { Text(item) },
                                leadingContent = {
                                    Icon(Icons.Default.Info, null)
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { selectedIndex = index }
                            )
                            HorizontalDivider()
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // DetailCard(items[selectedIndex])
                    DetailCard(
                        title = items[selectedIndex],
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.5f)
                    )
                }

            } else {
                //Tablet Layout (Row Two Pane)
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {

                    // Left pane
                    LazyColumn(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        itemsIndexed(items) { index, item ->
                            ListItem(
                                headlineContent = { Text(item) },
                                leadingContent = {
                                    Icon(Icons.Default.Info, null)
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { selectedIndex = index }
                            )
                            HorizontalDivider()
                        }
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    // Right pane (Box + Column mixed)
                    Box(
                        modifier = Modifier
                            .weight(2f)
                            .fillMaxHeight()
                    ) {
                        DetailCard(items[selectedIndex])
                    }
                }
            }
        }
    }
}
@Composable
fun DetailCard(title: String, modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {

        Card(
            // Card 只需要填满它的父容器 Box 即可
            modifier = Modifier.fillMaxSize(),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(title, style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Detail content for $title goes here.")
                Spacer(modifier = Modifier.height(12.dp))
                FilledTonalButton(onClick = {}) {
                    Text("Action")
                }
            }
        }
    }
}

