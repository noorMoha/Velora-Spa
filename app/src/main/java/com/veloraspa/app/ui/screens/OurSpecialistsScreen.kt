package com.veloraspa.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.veloraspa.app.data.MockDataSource
import com.veloraspa.app.model.Specialist
import com.veloraspa.app.ui.theme.DarkBrown

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OurSpecialistsScreen(
    onSpecialistClick: (Specialist) -> Unit = {}
) {
    val specialistsList = MockDataSource.specialistsList

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Our Specialists",
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(specialistsList) { specialist ->
                SpecialistItemCard(
                    specialist = specialist,
                    onClick = { onSpecialistClick(specialist) }
                )
            }
        }
    }
}

@Composable
fun SpecialistItemCard(
    specialist: Specialist,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = specialist.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = specialist.title,
                style = MaterialTheme.typography.bodyMedium,
                color = DarkBrown
            )
        }
    }
}