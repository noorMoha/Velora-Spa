package com.veloraspa.app.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.veloraspa.app.data.MockDataSource
import com.veloraspa.app.model.Booking
import com.veloraspa.app.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllAppointmentsScreen(
    onBackClick: () -> Unit = {}
) {
    val filters = listOf("All", "Pending", "Confirmed", "Completed", "Cancelled")
    var selectedFilter by remember { mutableStateOf("All") }

    val appointments = remember {
        mutableStateListOf<Booking>().apply { addAll(MockDataSource.bookingsList) }
    }

    val filteredAppointments = if (selectedFilter == "All") {
        appointments
    } else {
        appointments.filter { it.status == selectedFilter }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "All Appointments",
                            fontWeight = FontWeight.Bold,
                            color = DarkBrown,
                            fontSize = 20.sp
                        )
                        Text(
                            text = "Admin Management",
                            color = SoftBrown,
                            fontSize = 12.sp
                        )
                    }
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
            // شريط الفلاتر الأفقية
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                filters.forEach { filter ->
                    val isSelected = selectedFilter == filter
                    FilterChip(
                        selected = isSelected,
                        onClick = { selectedFilter = filter },
                        label = {
                            Text(
                                text = filter,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                color = if (isSelected) Color.White else DarkBrown
                            )
                        },
                        shape = RoundedCornerShape(50),
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = DarkBrown,
                            containerColor = CardBackground
                        ),
                        border = FilterChipDefaults.filterChipBorder(
                            enabled = true,
                            selected = isSelected,
                            borderColor = DarkBrown.copy(alpha = 0.3f)
                        )
                    )
                }
            }

            if (filteredAppointments.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No appointments found for \"$selectedFilter\".",
                        color = SoftBrown,
                        fontSize = 15.sp
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(filteredAppointments) { appointment ->
                        AdminAppointmentCard(
                            appointment = appointment,
                            onStatusChange = { newStatus ->
                                val index = appointments.indexOf(appointment)
                                if (index != -1) {
                                    appointments[index] = appointment.copy(status = newStatus)
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AdminAppointmentCard(
    appointment: Booking,
    onStatusChange: (String) -> Unit
) {
    val badgeBgColor = when (appointment.status) {
        "Confirmed" -> AccentGreen
        "Cancelled" -> AccentRed
        "Completed" -> DarkBrown.copy(alpha = 0.15f)
        else -> Color.White.copy(alpha = 0.85f)
    }

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
                Text(
                    text = "#${appointment.bookingId} • ${appointment.serviceName}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = DarkBrown,
                    modifier = Modifier.weight(1f)
                )

                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = badgeBgColor
                ) {
                    Text(
                        text = appointment.status,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkBrown
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Specialist",
                    tint = SoftBrown,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Specialist: ${appointment.specialistName}",
                    fontSize = 13.sp,
                    color = DarkBrown
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.CalendarToday,
                        contentDescription = "Date",
                        tint = SoftBrown,
                        modifier = Modifier.size(15.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = appointment.date,
                        fontSize = 13.sp,
                        color = DarkBrown
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Schedule,
                        contentDescription = "Time",
                        tint = SoftBrown,
                        modifier = Modifier.size(15.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = appointment.time,
                        fontSize = 13.sp,
                        color = DarkBrown
                    )
                }
            }

            if (appointment.status == "Pending" || appointment.status == "Confirmed") {
                Spacer(modifier = Modifier.height(14.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedButton(
                        onClick = { onStatusChange("Cancelled") },
                        modifier = Modifier.height(34.dp),
                        shape = RoundedCornerShape(50),
                        border = BorderStroke(1.dp, DarkBrown.copy(alpha = 0.5f)),
                        contentPadding = PaddingValues(horizontal = 14.dp)
                    ) {
                        Text(
                            text = "Cancel",
                            color = DarkBrown,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    if (appointment.status == "Pending") {
                        Button(
                            onClick = { onStatusChange("Confirmed") },
                            modifier = Modifier.height(34.dp),
                            shape = RoundedCornerShape(50),
                            colors = ButtonDefaults.buttonColors(containerColor = DarkBrown),
                            contentPadding = PaddingValues(horizontal = 14.dp)
                        ) {
                            Text(
                                text = "Confirm",
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    } else {
                        Button(
                            onClick = { onStatusChange("Completed") },
                            modifier = Modifier.height(34.dp),
                            shape = RoundedCornerShape(50),
                            colors = ButtonDefaults.buttonColors(containerColor = DarkBrown),
                            contentPadding = PaddingValues(horizontal = 14.dp)
                        ) {
                            Text(
                                text = "Complete",
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AllAppointmentsScreenPreview() {
    AllAppointmentsScreen()
}