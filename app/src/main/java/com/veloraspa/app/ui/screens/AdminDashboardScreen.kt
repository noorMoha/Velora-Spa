package com.veloraspa.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Badge
import androidx.compose.material.icons.outlined.EventNote
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.NotificationsNone
import androidx.compose.material.icons.outlined.PeopleAlt
import androidx.compose.material.icons.outlined.Spa
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.veloraspa.app.ui.theme.AppTypography
import com.veloraspa.app.ui.theme.DarkBrown
import com.veloraspa.app.ui.theme.SoftBrown
import com.veloraspa.app.ui.theme.VeloraspaTheme

@Composable
fun AdminDashboardScreen(
    onNavigateToManageServices: () -> Unit = {},
    onNavigateToAllAppointments: () -> Unit = {},
    onNavigateToSpecialists: () -> Unit = {},
    onNavigateToTracker: () -> Unit = {},
    onNavigateToConsumer: () -> Unit = {},
    onNavigateToStaffProfile: () -> Unit = {},
    onOpenDrawer: () -> Unit = {}
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            VeloraCustomerBottomNav(
                selectedTab = 0,
                onHomeClick = {},
                onBookingsClick = onNavigateToAllAppointments,
                onFavoritesClick = onNavigateToManageServices,
                onProfileClick = onNavigateToStaffProfile
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            item { Spacer(modifier = Modifier.height(6.dp)) }

            // Top Bar: Menu + Bell
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = onOpenDrawer) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu",
                            tint = DarkBrown,
                            modifier = Modifier.size(26.dp)
                        )
                    }
                    IconButton(
                        onClick = {},
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.surface)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.NotificationsNone,
                            contentDescription = "Notifications",
                            tint = DarkBrown
                        )
                    }
                }
            }

            // Greeting: "Welcome back, Manager!"
            item {
                Column {
                    Text(
                        text = "Welcome back, Manager!",
                        style = AppTypography.titleLarge.copy(fontSize = 25.sp)
                    )
                    Text(
                        text = "Welcome back, Manager!",
                        style = AppTypography.bodyMedium.copy(color = SoftBrown)
                    )
                }
            }

            // Metrics Row: 24 | 3 | 8
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    AdminMetricCard(
                        title = "Appointments\nToday",
                        count = "24",
                        modifier = Modifier.weight(1f)
                    )
                    AdminMetricCard(
                        title = "Pending\nCancellations",
                        count = "3",
                        modifier = Modifier.weight(1f)
                    )
                    AdminMetricCard(
                        title = "Roster\non Duty",
                        count = "8",
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            // Admin Shortcuts (2 rows x 3 items as in diagram)
            item {
                Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                    Text(
                        text = "Admin Shortcuts",
                        style = AppTypography.titleMedium
                    )

                    // Row 1: Manage Services | All Bookings | Specialists
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        AdminShortcutBox(
                            label = "Manage\nServices",
                            icon = Icons.Outlined.Spa,
                            onClick = onNavigateToManageServices,
                            modifier = Modifier.weight(1f)
                        )
                        AdminShortcutBox(
                            label = "All Bookings",
                            icon = Icons.Outlined.EventNote,
                            onClick = onNavigateToAllAppointments,
                            modifier = Modifier.weight(1f)
                        )
                        AdminShortcutBox(
                            label = "Specialists",
                            icon = Icons.Outlined.Badge,
                            onClick = onNavigateToSpecialists,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    // Row 2: Specialists | Tracker | Consumer
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        AdminShortcutBox(
                            label = "Specialists",
                            icon = Icons.Outlined.Badge,
                            onClick = onNavigateToSpecialists,
                            modifier = Modifier.weight(1f)
                        )
                        AdminShortcutBox(
                            label = "Tracker",
                            icon = Icons.Outlined.PeopleAlt,
                            onClick = onNavigateToTracker,
                            modifier = Modifier.weight(1f)
                        )
                        AdminShortcutBox(
                            label = "Consumer",
                            icon = Icons.Outlined.Groups,
                            onClick = onNavigateToConsumer,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(10.dp)) }
        }
    }
}

@Composable
private fun AdminMetricCard(
    title: String,
    count: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.height(115.dp),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                style = AppTypography.bodyMedium.copy(
                    color = DarkBrown,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 14.sp
                ),
                textAlign = TextAlign.Center
            )
            Text(
                text = count,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = DarkBrown
            )
        }
    }
}

@Composable
private fun AdminShortcutBox(
    label: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier
                .size(72.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.surface)
                .border(
                    width = 1.dp,
                    color = SoftBrown.copy(alpha = 0.25f),
                    shape = RoundedCornerShape(16.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = DarkBrown,
                modifier = Modifier.size(32.dp)
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = label,
            style = AppTypography.bodyMedium.copy(
                color = DarkBrown,
                fontWeight = FontWeight.Medium,
                lineHeight = 14.sp
            ),
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true, showSystemUi = true, name = "Admin Dashboard Preview")
@Composable
fun AdminDashboardScreenPreview() {
    VeloraspaTheme {
        AdminDashboardScreen()
    }
}