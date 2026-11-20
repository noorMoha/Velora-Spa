package com.veloraspa.app.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class AdminSpecialist(
    val id: Int,
    val name: String,
    val role: String,
    val rating: Double,
    val reviewsCount: Int,
    val services: String,
    val isActive: Boolean,
    val schedule: String = "Sun - Thu, 10:00 AM - 6:00 PM"
)

val mockAdminSpecialists = listOf(
    AdminSpecialist(
        id = 1,
        name = "Evelyn Vance",
        role = "Lead Massage Specialist",
        rating = 4.5,
        reviewsCount = 141,
        services = "Signature Massage, Deep Tissue, Aromatherapy",
        isActive = true,
        schedule = "Sun - Thu, 9:00 AM - 5:00 PM"
    ),
    AdminSpecialist(
        id = 2,
        name = "Sophia Chen",
        role = "Senior Esthetician",
        rating = 3.9,
        reviewsCount = 93,
        services = "HydraFacial, Anti-aging, Facial Massage",
        isActive = true,
        schedule = "Mon - Fri, 11:00 AM - 7:00 PM"
    ),
    AdminSpecialist(
        id = 3,
        name = "Elena Rostova",
        role = "Body Care Expert",
        rating = 4.3,
        reviewsCount = 73,
        services = "Body Scrubs, Detox Wraps",
        isActive = false,
        schedule = "Sat - Wed, 10:00 AM - 6:00 PM"
    )
)

private val PinkBackground = Color(0xFFF9E6EA)
private val CardCream = Color(0xFFF2ECD7)
private val ButtonCream = Color(0xFFFAF6E8)
private val DarkBrownBorder = Color(0xFF5D4037)
private val ActiveGreenBg = Color(0xFFC8E6C9)
private val ActiveGreenBorder = Color(0xFF66BB6A)
private val AwayPinkBg = Color(0xFFF49FB0)
private val AwayPinkBorder = Color(0xFFC25E70)
private val RatingPillBg = Color(0xFFF5DDE3)
private val StarGold = Color(0xFFE6B84C)

@Composable
fun StaffAndReviewsScreen(
    onBackClick: () -> Unit = {}
) {
    val specialists = remember { mutableStateListOf(*mockAdminSpecialists.toTypedArray()) }

    var showAddDialog by remember { mutableStateOf(false) }
    var editingSpecialist by remember { mutableStateOf<AdminSpecialist?>(null) }
    var schedulingSpecialist by remember { mutableStateOf<AdminSpecialist?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PinkBackground)
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Admin Our Specialists",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(8.dp))


        OutlinedButton(
            onClick = { showAddDialog = true },
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
                .padding(horizontal = 8.dp),
            shape = RoundedCornerShape(28.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = ButtonCream,
                contentColor = Color.Black
            ),
            border = BorderStroke(2.dp, DarkBrownBorder)
        ) {
            Text(
                text = "+ Add New Specialist",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))


        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(14.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(specialists, key = { it.id }) { specialist ->
                SpecialistAdminCard(
                    specialist = specialist,
                    onEditClick = { editingSpecialist = specialist },
                    onManageScheduleClick = { schedulingSpecialist = specialist }
                )
            }
        }
    }


    if (showAddDialog) {
        SpecialistFormDialog(
            title = "Add New Specialist",
            initialSpecialist = null,
            onDismiss = { showAddDialog = false },
            onSave = { name, role, services, isActive ->
                val newId = (specialists.maxOfOrNull { it.id } ?: 0) + 1
                specialists.add(
                    0,
                    AdminSpecialist(
                        id = newId,
                        name = name,
                        role = role,
                        rating = 5.0,
                        reviewsCount = 0,
                        services = services,
                        isActive = isActive
                    )
                )
                showAddDialog = false
            }
        )
    }


    editingSpecialist?.let { current ->
        SpecialistFormDialog(
            title = "Edit Specialist",
            initialSpecialist = current,
            onDismiss = { editingSpecialist = null },
            onSave = { name, role, services, isActive ->
                val index = specialists.indexOfFirst { it.id == current.id }
                if (index != -1) {
                    specialists[index] = current.copy(
                        name = name,
                        role = role,
                        services = services,
                        isActive = isActive
                    )
                }
                editingSpecialist = null
            }
        )
    }


    schedulingSpecialist?.let { current ->
        ManageScheduleDialog(
            specialist = current,
            onDismiss = { schedulingSpecialist = null },
            onSave = { newSchedule ->
                val index = specialists.indexOfFirst { it.id == current.id }
                if (index != -1) {
                    specialists[index] = current.copy(schedule = newSchedule)
                }
                schedulingSpecialist = null
            }
        )
    }
}

@Composable
fun SpecialistAdminCard(
    specialist: AdminSpecialist,
    onEditClick: () -> Unit,
    onManageScheduleClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardCream),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(76.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFD7CCC8))
                        .border(2.dp, DarkBrownBorder, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = specialist.name,
                        modifier = Modifier.size(44.dp),
                        tint = DarkBrownBorder
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))

                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = specialist.name,
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                    Text(
                        text = specialist.role,
                        fontSize = 13.sp,
                        color = Color(0xFF333333)
                    )
                    Spacer(modifier = Modifier.height(6.dp))

                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(RatingPillBg)
                            .padding(horizontal = 10.dp, vertical = 3.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Rating",
                            tint = StarGold,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "${specialist.rating} (${specialist.reviewsCount})",
                            fontSize = 12.sp,
                            color = Color.Black
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Services: ")
                        }
                        append(specialist.services)
                    },
                    fontSize = 13.sp,
                    color = Color.Black,
                    lineHeight = 18.sp,
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(8.dp))

                val statusBg = if (specialist.isActive) ActiveGreenBg else AwayPinkBg
                val statusBorder = if (specialist.isActive) ActiveGreenBorder else AwayPinkBorder
                val statusText = if (specialist.isActive) "Active" else "Away"

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(statusBg)
                        .border(1.dp, statusBorder, RoundedCornerShape(50))
                        .padding(horizontal = 14.dp, vertical = 5.dp)
                ) {
                    Text(
                        text = statusText,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    onClick = onEditClick,
                    modifier = Modifier
                        .weight(1f)
                        .height(36.dp),
                    shape = RoundedCornerShape(18.dp),
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = ButtonCream,
                        contentColor = Color.Black
                    ),
                    border = BorderStroke(1.5.dp, Color.Black)
                ) {
                    Text(text = "Edit", fontSize = 13.sp, fontWeight = FontWeight.Medium)
                }

                OutlinedButton(
                    onClick = onManageScheduleClick,
                    modifier = Modifier
                        .weight(1f)
                        .height(36.dp),
                    shape = RoundedCornerShape(18.dp),
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = ButtonCream,
                        contentColor = Color.Black
                    ),
                    border = BorderStroke(1.5.dp, Color.Black)
                ) {
                    Text(text = "Manage Schedule", fontSize = 13.sp, fontWeight = FontWeight.Medium)
                }
            }
        }
    }
}

@Composable
fun SpecialistFormDialog(
    title: String,
    initialSpecialist: AdminSpecialist?,
    onDismiss: () -> Unit,
    onSave: (name: String, role: String, services: String, isActive: Boolean) -> Unit
) {
    var name by remember { mutableStateOf(initialSpecialist?.name ?: "") }
    var role by remember { mutableStateOf(initialSpecialist?.role ?: "") }
    var services by remember { mutableStateOf(initialSpecialist?.services ?: "") }
    var isActive by remember { mutableStateOf(initialSpecialist?.isActive ?: true) }

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = ButtonCream,
        title = { Text(text = title, fontWeight = FontWeight.Bold, color = Color.Black) },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") },
                    singleLine = true
                )
                OutlinedTextField(
                    value = role,
                    onValueChange = { role = it },
                    label = { Text("Role / Specialty") },
                    singleLine = true
                )
                OutlinedTextField(
                    value = services,
                    onValueChange = { services = it },
                    label = { Text("Services") }
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = if (isActive) "Status: Active" else "Status: Away",
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                    Switch(
                        checked = isActive,
                        onCheckedChange = { isActive = it }
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (name.isNotBlank()) {
                        onSave(name.trim(), role.trim(), services.trim(), isActive)
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = DarkBrownBorder)
            ) {
                Text("Save", color = Color.White)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel", color = DarkBrownBorder)
            }
        }
    )
}

@Composable
fun ManageScheduleDialog(
    specialist: AdminSpecialist,
    onDismiss: () -> Unit,
    onSave: (String) -> Unit
) {
    var scheduleText by remember { mutableStateOf(specialist.schedule) }

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = ButtonCream,
        title = {
            Text(
                text = "Schedule - ${specialist.name}",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 18.sp
            )
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "Working Days & Hours:",
                    fontSize = 13.sp,
                    color = Color.DarkGray
                )
                OutlinedTextField(
                    value = scheduleText,
                    onValueChange = { scheduleText = it },
                    label = { Text("Schedule") }
                )
            }
        },
        confirmButton = {
            Button(
                onClick = { onSave(scheduleText.trim()) },
                colors = ButtonDefaults.buttonColors(containerColor = DarkBrownBorder)
            ) {
                Text("Update", color = Color.White)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel", color = DarkBrownBorder)
            }
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun StaffAndReviewsScreenPreview() {
    StaffAndReviewsScreen()
}