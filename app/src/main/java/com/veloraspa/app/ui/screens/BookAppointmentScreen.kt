package com.veloraspa.app.ui.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.veloraspa.app.ui.theme.*


private val CalendarAccentSurface = Color(0xFFF3B8BE)
private val UnselectedSlotSurface = Color(0xFFF3D4D8)
private val SpecialistCardSurface = Color(0xFFC5A379)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookAppointmentScreen(
    specialistName: String = "Sophia Chen",
    specialistRole: String = "Senior Esthetician",
    specialistRating: String = "3.9 (93)",
    onBackClick: () -> Unit = {},
    onBookingConfirmed: (date: String, time: String, specialist: String) -> Unit = { _, _, _ -> }
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    // ديسمبر 2026 يبدأ يوم الثلاثاء (2 خانات فارغة للأحد والاثنين)
    val leadingBlanks = 2
    val daysInMonth = (1..31).toList()

    val timeSlots = listOf(
        "10:00 AM", "12:30 PM", "12:00 AM",
        "12:20 PM", "2:00 PM", "5:00 PM"
    )

    var selectedDay by remember { mutableIntStateOf(8) }
    var selectedTime by remember { mutableStateOf("10:00 AM") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 16.dp)
                            .clip(RoundedCornerShape(50))
                            .background(CardBackground)
                            .padding(horizontal = 10.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(SoftBrown.copy(alpha = 0.45f))
                                .clickable { onBackClick() },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.ChevronLeft,
                                contentDescription = "Back",
                                tint = DarkBrown,
                                modifier = Modifier.size(22.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Text(
                            text = "Book an Appointment",
                            fontWeight = FontWeight.Bold,
                            color = DarkBrown,
                            fontSize = 18.sp
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
                .verticalScroll(scrollState)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 1. تقويم ديسمبر 2026
            AppointmentCalendarCard(
                yearText = "2026",
                monthText = "DECEMBER",
                leadingBlanks = leadingBlanks,
                days = daysInMonth,
                selectedDay = selectedDay,
                onSelectDay = { selectedDay = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 2. قسم الأوقات المتاحة
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Time",
                    fontWeight = FontWeight.Bold,
                    color = DarkBrown,
                    fontSize = 19.sp
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                timeSlots.chunked(3).forEach { rowSlots ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        rowSlots.forEach { slot ->
                            val isSelected = slot == selectedTime
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(42.dp)
                                    .clip(RoundedCornerShape(50))
                                    .background(if (isSelected) DarkBrown else UnselectedSlotSurface)
                                    .clickable { selectedTime = slot },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = slot,
                                    color = if (isSelected) Color.White else DarkBrown,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 1
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 3. بطاقة الأخصائية
            BookingSpecialistCard(
                name = specialistName,
                role = specialistRole,
                rating = specialistRating
            )

            Spacer(modifier = Modifier.height(18.dp))

            // 4. زر تأكيد الحجز
            Button(
                onClick = {
                    val formattedDate = "Dec $selectedDay, 2026"
                    onBookingConfirmed(formattedDate, selectedTime, specialistName)
                    Toast.makeText(
                        context,
                        "Booked $formattedDate at $selectedTime",
                        Toast.LENGTH_SHORT
                    ).show()
                },
                modifier = Modifier
                    .fillMaxWidth(0.76f)
                    .height(48.dp),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = DarkBrown)
            ) {
                Text(
                    text = "Confirm Booking",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
private fun AppointmentCalendarCard(
    yearText: String,
    monthText: String,
    leadingBlanks: Int,
    days: List<Int>,
    selectedDay: Int,
    onSelectDay: (Int) -> Unit
) {
    val weekDays = listOf("SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT")
    val totalCells = leadingBlanks + days.size
    val remainder = totalCells % 7
    val trailingBlanks = if (remainder == 0) 0 else 7 - remainder

    val calendarCells: List<Int?> = buildList {
        repeat(leadingBlanks) { add(null) }
        addAll(days)
        repeat(trailingBlanks) { add(null) }
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        border = BorderStroke(1.5.dp, DarkBrown),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(4.dp),
                    color = CalendarAccentSurface
                ) {
                    Text(
                        text = yearText,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkBrown
                    )
                }

                Text(
                    text = monthText,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkBrown,
                    letterSpacing = 1.sp
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(CalendarAccentSurface)
                    .padding(vertical = 4.dp)
            ) {
                weekDays.forEach { dayTitle ->
                    Text(
                        text = dayTitle,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkBrown
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(7),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(205.dp),
                userScrollEnabled = false,
                horizontalArrangement = Arrangement.spacedBy(2.dp),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                items(calendarCells) { day ->
                    if (day == null) {
                        Box(
                            modifier = Modifier
                                .aspectRatio(1f)
                                .border(0.5.dp, SoftBrown.copy(alpha = 0.35f))
                        )
                    } else {
                        val isSelected = day == selectedDay
                        Box(
                            modifier = Modifier
                                .aspectRatio(1f)
                                .border(
                                    width = if (isSelected) 1.5.dp else 0.5.dp,
                                    color = if (isSelected) DarkBrown else SoftBrown.copy(alpha = 0.35f)
                                )
                                .background(
                                    if (isSelected) CalendarAccentSurface.copy(alpha = 0.5f)
                                    else Color.Transparent
                                )
                                .clickable { onSelectDay(day) },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = day.toString(),
                                fontSize = 13.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                color = DarkBrown
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun BookingSpecialistCard(
    name: String,
    role: String,
    rating: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = SpecialistCardSurface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(62.dp)
                    .clip(CircleShape)
                    .background(CardBackground),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = name,
                    tint = DarkBrown,
                    modifier = Modifier.size(36.dp)
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column {
                Text(
                    text = name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = DarkBrown
                )
                Text(
                    text = role,
                    fontSize = 13.sp,
                    color = SoftBrown
                )
                Spacer(modifier = Modifier.height(2.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Rating",
                        tint = StarYellow,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = rating,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkBrown
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BookAppointmentScreenPreview() {
    VeloraspaTheme {
        BookAppointmentScreen()
    }
}