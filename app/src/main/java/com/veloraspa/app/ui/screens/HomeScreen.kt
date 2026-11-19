package com.veloraspa.app.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.ContentCut
import androidx.compose.material.icons.outlined.FaceRetouchingNatural
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.NotificationsNone
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.SelfImprovement
import androidx.compose.material.icons.outlined.Spa
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.veloraspa.app.R
import com.veloraspa.app.ui.theme.AppTypography
import com.veloraspa.app.ui.theme.DarkBrown
import com.veloraspa.app.ui.theme.PinkBackground
import com.veloraspa.app.ui.theme.SoftBrown
import com.veloraspa.app.ui.theme.VeloraspaTheme

data class SpaCategory(val name: String, val icon: ImageVector)

data class PopularTreatment(
    val id: String,
    val title: String,
    val subtitle: String,
    @DrawableRes val imageRes: Int
)

@Composable
fun HomeScreen(
    @DrawableRes featuredBannerRes: Int = R.drawable.massage,
    onNavigateToServices: (String) -> Unit = {},
    onNavigateToServiceDetails: (String) -> Unit = {},
    onNavigateToBookAppointment: () -> Unit = {},
    onNavigateToBookings: () -> Unit = {},
    onNavigateToFavorites: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {}
) {
    val categories = listOf(
        SpaCategory("Massage", Icons.Outlined.Spa),
        SpaCategory("Facials", Icons.Outlined.FaceRetouchingNatural),
        SpaCategory("Hair", Icons.Outlined.ContentCut),
        SpaCategory("Body", Icons.Outlined.SelfImprovement)
    )

    // Replace these drawable names with your actual files inside res/drawable/
    val popularTreatments = listOf(
        PopularTreatment("1", "Signature Massage", "Relax & renew", R.drawable.signature_massage),
        PopularTreatment("2", "Body Tone", "Firm & refresh", R.drawable.body_tone),
        PopularTreatment("3", "Body Scrub", "Smooth skin", R.drawable.body_scrub)
    )

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            VeloraCustomerBottomNav(
                selectedTab = 0,
                onHomeClick = {},
                onBookingsClick = onNavigateToBookings,
                onFavoritesClick = onNavigateToFavorites,
                onProfileClick = onNavigateToProfile
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item { Spacer(modifier = Modifier.height(6.dp)) }

            // Top greeting + notification bell
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Column {
                        Text(
                            text = "Hello, Beautiful!",
                            style = AppTypography.titleLarge.copy(
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Text(
                            text = "Welcome to spa bookings!",
                            style = AppTypography.bodyMedium.copy(
                                fontStyle = FontStyle.Italic,
                                color = SoftBrown
                            )
                        )
                    }
                    IconButton(
                        onClick = {},
                        modifier = Modifier.size(36.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.NotificationsNone,
                            contentDescription = "Notifications",
                            tint = SoftBrown,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                }
            }

            // Featured Packages banner image + pagination dots
            item {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(148.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Image(
                                painter = painterResource(id = featuredBannerRes),
                                contentDescription = "Featured Packages",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )

                            // Subtle dark gradient scrim for readable text & button
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(
                                        Brush.horizontalGradient(
                                            colors = listOf(
                                                Color.Black.copy(alpha = 0.55f),
                                                Color.Black.copy(alpha = 0.20f)
                                            )
                                        )
                                    )
                            )

                            Column(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .padding(14.dp),
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column {
                                    Text(
                                        text = "Featured Packages",
                                        fontSize = 17.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = "Relaxing packages and\nholistic treatments",
                                        fontSize = 11.sp,
                                        lineHeight = 14.sp,
                                        color = Color.White.copy(alpha = 0.92f)
                                    )
                                }

                                Button(
                                    onClick = onNavigateToBookAppointment,
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = DarkBrown,
                                        contentColor = Color.White
                                    ),
                                    shape = RoundedCornerShape(8.dp),
                                    contentPadding = PaddingValues(horizontal = 14.dp, vertical = 0.dp),
                                    modifier = Modifier.height(30.dp)
                                ) {
                                    Text(
                                        text = "Book Now",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                        repeat(3) {
                            Box(
                                modifier = Modifier
                                    .size(5.dp)
                                    .clip(CircleShape)
                                    .background(SoftBrown.copy(alpha = 0.45f))
                            )
                        }
                    }
                }
            }

            // Quick Category row
            item {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "Quick Category",
                        style = AppTypography.titleMedium.copy(fontSize = 16.sp)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        categories.forEach { category ->
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .clickable { onNavigateToServices(category.name) }
                                    .padding(vertical = 2.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(58.dp)
                                        .clip(RoundedCornerShape(14.dp))
                                        .background(MaterialTheme.colorScheme.surface),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = category.icon,
                                        contentDescription = category.name,
                                        tint = SoftBrown,
                                        modifier = Modifier.size(28.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = category.name,
                                    style = AppTypography.bodyMedium.copy(
                                        color = DarkBrown,
                                        fontSize = 11.sp
                                    )
                                )
                            }
                        }
                    }
                }
            }

            // Popular Treatments row with actual images
            item {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Popular Treatments",
                            style = AppTypography.titleMedium.copy(fontSize = 16.sp)
                        )
                        Text(
                            text = "See all",
                            style = AppTypography.bodyMedium.copy(
                                color = SoftBrown,
                                fontSize = 11.sp
                            ),
                            modifier = Modifier.clickable { onNavigateToServices("All") }
                        )
                    }

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(popularTreatments) { item ->
                            Card(
                                modifier = Modifier
                                    .width(114.dp)
                                    .clickable { onNavigateToServiceDetails(item.id) },
                                shape = RoundedCornerShape(12.dp),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                            ) {
                                Column(modifier = Modifier.padding(6.dp)) {
                                    Image(
                                        painter = painterResource(id = item.imageRes),
                                        contentDescription = item.title,
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(72.dp)
                                            .clip(RoundedCornerShape(10.dp))
                                    )
                                    Spacer(modifier = Modifier.height(5.dp))
                                    Text(
                                        text = item.title,
                                        style = AppTypography.bodyMedium.copy(
                                            color = DarkBrown,
                                            fontWeight = FontWeight.SemiBold,
                                            fontSize = 11.sp
                                        ),
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                    Text(
                                        text = item.subtitle,
                                        style = AppTypography.bodyMedium.copy(
                                            color = SoftBrown,
                                            fontSize = 10.sp
                                        ),
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            }
                        }
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(6.dp)) }
        }
    }
}

@Composable
fun VeloraCustomerBottomNav(
    selectedTab: Int,
    onHomeClick: () -> Unit,
    onBookingsClick: () -> Unit,
    onFavoritesClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
        tonalElevation = 4.dp
    ) {
        NavigationBarItem(
            selected = selectedTab == 0,
            onClick = onHomeClick,
            icon = {
                Icon(
                    imageVector = if (selectedTab == 0) Icons.Filled.Home else Icons.Outlined.Home,
                    contentDescription = "Home"
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = DarkBrown,
                indicatorColor = PinkBackground,
                unselectedIconColor = SoftBrown
            )
        )
        NavigationBarItem(
            selected = selectedTab == 1,
            onClick = onBookingsClick,
            icon = {
                Icon(
                    imageVector = Icons.Outlined.CalendarMonth,
                    contentDescription = "Bookings"
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = DarkBrown,
                indicatorColor = PinkBackground,
                unselectedIconColor = SoftBrown
            )
        )
        NavigationBarItem(
            selected = selectedTab == 2,
            onClick = onFavoritesClick,
            icon = {
                Icon(
                    imageVector = if (selectedTab == 2) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = "Favorites"
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = DarkBrown,
                indicatorColor = PinkBackground,
                unselectedIconColor = SoftBrown
            )
        )
        NavigationBarItem(
            selected = selectedTab == 3,
            onClick = onProfileClick,
            icon = {
                Icon(
                    imageVector = if (selectedTab == 3) Icons.Filled.Person else Icons.Outlined.Person,
                    contentDescription = "Profile"
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = DarkBrown,
                indicatorColor = PinkBackground,
                unselectedIconColor = SoftBrown
            )
        )
    }
}

@Preview(showBackground = true, showSystemUi = true, name = "Home Screen Preview")
@Composable
fun HomeScreenPreview() {
    VeloraspaTheme {
        HomeScreen()
    }
}