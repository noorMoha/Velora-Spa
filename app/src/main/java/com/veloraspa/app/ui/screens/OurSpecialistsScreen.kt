package com.veloraspa.app.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.veloraspa.app.data.MockDataSource
import com.veloraspa.app.model.Specialist
import com.veloraspa.app.ui.theme.CardBackground
import com.veloraspa.app.ui.theme.DarkBrown
import com.veloraspa.app.ui.theme.PinkBackground
import com.veloraspa.app.ui.theme.StarYellow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OurSpecialistsScreen(
    onBackClick: () -> Unit = {},
    onSearchClick: () -> Unit = {},
    onSelectClick: (Specialist) -> Unit = {}
) {
    val specialistsList = MockDataSource.specialistsList

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Our Specialists",
                        fontWeight = FontWeight.Bold,
                        color = DarkBrown,
                        fontSize = 20.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = DarkBrown
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onSearchClick) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = DarkBrown
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = PinkBackground)
            )
        },
        containerColor = PinkBackground
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(specialistsList) { specialist ->
                SpecialistItemCard(
                    specialist = specialist,
                    onSelectClick = { onSelectClick(specialist) }
                )
            }
        }
    }
}

@Composable
fun SpecialistItemCard(
    specialist: Specialist,
    onSelectClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .size(65.dp)
                        .clip(CircleShape)
                        .background(DarkBrown.copy(alpha = 0.15f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = specialist.name.take(1),
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = DarkBrown
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = specialist.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = DarkBrown,
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = specialist.title,
                        style = MaterialTheme.typography.bodyMedium,
                        color = DarkBrown.copy(alpha = 0.8f),
                        fontSize = 13.sp
                    )
                    Spacer(modifier = Modifier.height(6.dp))

                    //Rating badge
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = Color.White.copy(alpha = 0.6f),
                        modifier = Modifier.wrapContentSize()
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = "Rating",
                                tint = StarYellow,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "3.9 (93)",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = DarkBrown
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))


            Text(
                text = "Services: Signature Massage Deep Tissue Aromatherapy",
                style = MaterialTheme.typography.bodySmall,
                color = DarkBrown,
                fontSize = 12.sp,
                lineHeight = 16.sp
            )

            Spacer(modifier = Modifier.height(14.dp))


            Button(
                onClick = onSelectClick,
                modifier = Modifier
                    .align(Alignment.End)
                    .height(34.dp)
                    .width(90.dp),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                border = BorderStroke(1.dp, DarkBrown),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = "Select",
                    color = DarkBrown,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OurSpecialistsScreenPreview() {
    OurSpecialistsScreen()
}