package com.veloraspa.app.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Schedule
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
fun ManageBookingsScreen(
    onBackClick: () -> Unit = {},
    onRescheduleClick: (Booking) -> Unit = {}
) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("Upcoming", "History")

    val bookings = remember {
        mutableStateListOf<Booking>().apply { addAll(MockDataSource.bookingsList) }
    }

    val filteredBookings = if (selectedTab == 0) {
        bookings.filter { it.status == "Confirmed" || it.status == "Pending" }
    } else {
        bookings.filter { it.status == "Completed" || it.status == "Cancelled" }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Manage Bookings",
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

            if (filteredBookings.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No bookings found in this section.",
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
                    items(filteredBookings) { booking ->
                        BookingItemCard(
                            booking = booking,
                            isUpcoming = selectedTab == 0,
                            onCancelClick = {
                                val index = bookings.indexOf(booking)
                                if (index != -1) {
                                    bookings[index] = booking.copy(status = "Cancelled")
                                }
                            },
                            onRescheduleClick = { onRescheduleClick(booking) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BookingItemCard(
    booking: Booking,
    isUpcoming: Boolean,
    onCancelClick: () -> Unit,
    onRescheduleClick: () -> Unit
) {
    val badgeBgColor = when (booking.status) {
        "Confirmed" -> AccentGreen
        "Cancelled" -> AccentRed
        else -> Color.White.copy(alpha = 0.8f)
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
                    text = booking.serviceName,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = DarkBrown
                )

                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = badgeBgColor
                ) {
                    Text(
                        text = booking.status,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkBrown
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Booking ID: #${booking.bookingId}",
                fontSize = 12.sp,
                color = SoftBrown
            )

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
                    text = booking.specialistName,
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
                        text = booking.date,
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
                        text = booking.time,
                        fontSize = 13.sp,
                        color = DarkBrown
                    )
                }
            }

            if (isUpcoming) {
                Spacer(modifier = Modifier.height(14.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedButton(
                        onClick = onCancelClick,
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

                    Button(
                        onClick = onRescheduleClick,
                        modifier = Modifier.height(34.dp),
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(containerColor = DarkBrown),
                        contentPadding = PaddingValues(horizontal = 14.dp)
                    ) {
                        Text(
                            text = "Reschedule",
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

@Preview(showBackground = true)
@Composable
fun ManageBookingsScreenPreview() {
    ManageBookingsScreen()
}