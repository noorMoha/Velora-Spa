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
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.veloraspa.app.data.MockDataSource
import com.veloraspa.app.model.Service
import com.veloraspa.app.model.Specialist
import com.veloraspa.app.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    onBackClick: () -> Unit = {},
    onBookServiceClick: (Service) -> Unit = {},
    onSelectSpecialistClick: (Specialist) -> Unit = {}
) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("Services", "Specialists")

    val favoriteServices = remember {
        mutableStateListOf<Service>().apply { addAll(MockDataSource.servicesList) }
    }
    val favoriteSpecialists = remember {
        mutableStateListOf<Specialist>().apply { addAll(MockDataSource.specialistsList.take(2)) }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "My Favorites",
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
                colors = TopAppBarDefaults.topAppBarColors(containerColor = PinkBackground)
            )
        },
        containerColor = PinkBackground
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                tabs.forEachIndexed { index, title ->
                    val isSelected = selectedTab == index
                    Button(
                        onClick = { selectedTab = index },
                        modifier = Modifier
                            .weight(1f)
                            .height(40.dp),
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isSelected) DarkBrown else CardBackground
                        ),
                        border = if (!isSelected) BorderStroke(1.dp, DarkBrown.copy(alpha = 0.3f)) else null
                    ) {
                        Text(
                            text = title,
                            color = if (isSelected) Color.White else DarkBrown,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }
                }
            }

            if (selectedTab == 0) {
                if (favoriteServices.isEmpty()) {
                    EmptyFavoritesState(message = "No favorite services saved yet.")
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(favoriteServices) { service ->
                            FavoriteServiceCard(
                                service = service,
                                onRemoveFavorite = { favoriteServices.remove(service) },
                                onBookClick = { onBookServiceClick(service) }
                            )
                        }
                    }
                }
            } else {
                if (favoriteSpecialists.isEmpty()) {
                    EmptyFavoritesState(message = "No favorite specialists saved yet.")
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(favoriteSpecialists) { specialist ->
                            FavoriteSpecialistCard(
                                specialist = specialist,
                                onRemoveFavorite = { favoriteSpecialists.remove(specialist) },
                                onSelectClick = { onSelectSpecialistClick(specialist) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun EmptyFavoritesState(message: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            color = SoftBrown,
            fontSize = 15.sp
        )
    }
}

@Composable
fun FavoriteServiceCard(
    service: Service,
    onRemoveFavorite: () -> Unit,
    onBookClick: () -> Unit
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
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = PinkBackground
                    ) {
                        Text(
                            text = service.category,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = DarkBrown
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = service.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = DarkBrown
                    )
                }

                IconButton(onClick = onRemoveFavorite) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Remove Favorite",
                        tint = Color(0xFFD32F2F)
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = service.description,
                fontSize = 13.sp,
                color = DarkBrown.copy(alpha = 0.8f),
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "$${service.price.toInt()}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = DarkBrown
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Icon(
                        imageVector = Icons.Default.Schedule,
                        contentDescription = "Duration",
                        tint = SoftBrown,
                        modifier = Modifier.size(15.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = service.duration,
                        fontSize = 12.sp,
                        color = SoftBrown
                    )
                }

                Button(
                    onClick = onBookClick,
                    modifier = Modifier.height(34.dp),
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(containerColor = DarkBrown),
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    Text(
                        text = "Book Now",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun FavoriteSpecialistCard(
    specialist: Specialist,
    onRemoveFavorite: () -> Unit,
    onSelectClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(DarkBrown.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = specialist.name.take(1),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = DarkBrown
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = specialist.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = DarkBrown
                )
                Text(
                    text = specialist.title,
                    fontSize = 12.sp,
                    color = SoftBrown
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Rating",
                        tint = StarYellow,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${specialist.rating}",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkBrown
                    )
                }
            }

            Column(horizontalAlignment = Alignment.End) {
                IconButton(
                    onClick = onRemoveFavorite,
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Remove Favorite",
                        tint = Color(0xFFD32F2F),
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedButton(
                    onClick = onSelectClick,
                    modifier = Modifier.height(30.dp),
                    shape = RoundedCornerShape(50),
                    border = BorderStroke(1.dp, DarkBrown),
                    contentPadding = PaddingValues(horizontal = 12.dp)
                ) {
                    Text(
                        text = "Select",
                        color = DarkBrown,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavoritesScreenPreview() {
    FavoritesScreen()
}