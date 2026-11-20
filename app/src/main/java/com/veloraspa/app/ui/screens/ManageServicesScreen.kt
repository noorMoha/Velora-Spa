package com.veloraspa.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class ManagedServiceItem(
    val id: Int,
    val name: String,
    val durationMinutes: Int,
    val isActive: Boolean = true
)

@Composable
fun ManageServicesScreen(
    onBackClick: () -> Unit = {}
) {
    val backgroundPink = Color(0xFFF3E5E6)
    val darkBrown = Color(0xFF5D3A24)
    val cardCream = Color(0xFFF3EAD8)
    val mutedGold = Color(0xFFA68B67)
    val textDark = Color(0xFF3E2723)

    val services = remember {
        mutableStateListOf(
            ManagedServiceItem(1, "Signature Massage", 60, true),
            ManagedServiceItem(2, "HydraFacial Treatment", 45, true),
            ManagedServiceItem(3, "Body Scrub", 60, true),
            ManagedServiceItem(4, "Hair Treatment", 60, true),
            ManagedServiceItem(5, "Deep Tissue Massage", 75, true)
        )
    }

    var showDialog by remember { mutableStateOf(false) }
    var editingService by remember { mutableStateOf<ManagedServiceItem?>(null) }
    var serviceNameInput by remember { mutableStateOf("") }
    var serviceDurationInput by remember { mutableStateOf("") }

    Scaffold(
        containerColor = backgroundPink,
        bottomBar = {
            Column {
                HorizontalDivider(color = Color(0xFFD7C6C7), thickness = 1.dp)
                NavigationBar(
                    containerColor = backgroundPink,
                    tonalElevation = 0.dp,
                    modifier = Modifier.height(64.dp)
                ) {
                    NavigationBarItem(
                        selected = true,
                        onClick = {},
                        icon = {
                            Icon(
                                imageVector = Icons.Filled.Home,
                                contentDescription = "Home",
                                modifier = Modifier.size(30.dp),
                                tint = Color.Black
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = Color.Transparent
                        )
                    )
                    NavigationBarItem(
                        selected = false,
                        onClick = {},
                        icon = {
                            Icon(
                                imageVector = Icons.Outlined.CalendarToday,
                                contentDescription = "Bookings",
                                modifier = Modifier.size(28.dp),
                                tint = Color(0xFF333333)
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = Color.Transparent
                        )
                    )
                    NavigationBarItem(
                        selected = false,
                        onClick = {},
                        icon = {
                            Icon(
                                imageVector = Icons.Outlined.FavoriteBorder,
                                contentDescription = "Favorites",
                                modifier = Modifier.size(30.dp),
                                tint = Color(0xFF333333)
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = Color.Transparent
                        )
                    )
                    NavigationBarItem(
                        selected = false,
                        onClick = {},
                        icon = {
                            Icon(
                                imageVector = Icons.Outlined.PersonOutline,
                                contentDescription = "Profile",
                                modifier = Modifier.size(30.dp),
                                tint = Color(0xFF333333)
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = Color.Transparent
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.Black,
                        modifier = Modifier.size(28.dp)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Manage Services",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1F1A1A)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    editingService = null
                    serviceNameInput = ""
                    serviceDurationInput = "60"
                    showDialog = true
                },
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = darkBrown,
                    contentColor = Color(0xFFEADBC8)
                ),
                modifier = Modifier
                    .fillMaxWidth(0.78f)
                    .height(52.dp)
            ) {
                Text(
                    text = "Add New Service",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(22.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Service",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "Edit/Delete",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                items(services, key = { it.id }) { service ->
                    Surface(
                        shape = RoundedCornerShape(26.dp),
                        color = cardCream,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 18.dp, vertical = 14.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = service.name,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = textDark
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = "mins ${service.durationMinutes}",
                                    fontSize = 13.sp,
                                    color = textDark
                                )
                            }

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Switch(
                                    checked = service.isActive,
                                    onCheckedChange = { checked ->
                                        val index = services.indexOfFirst { it.id == service.id }
                                        if (index != -1) {
                                            services[index] = service.copy(isActive = checked)
                                        }
                                    },
                                    colors = SwitchDefaults.colors(
                                        checkedThumbColor = Color.White,
                                        checkedTrackColor = mutedGold,
                                        uncheckedThumbColor = Color.White,
                                        uncheckedTrackColor = Color(0xFFD5C8B8),
                                        uncheckedBorderColor = Color.Transparent,
                                        checkedBorderColor = Color.Transparent
                                    )
                                )

                                IconButton(
                                    onClick = { services.remove(service) },
                                    modifier = Modifier.size(34.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.Delete,
                                        contentDescription = "Delete Service",
                                        tint = mutedGold,
                                        modifier = Modifier.size(24.dp)
                                    )
                                }

                                IconButton(
                                    onClick = {
                                        editingService = service
                                        serviceNameInput = service.name
                                        serviceDurationInput = service.durationMinutes.toString()
                                        showDialog = true
                                    },
                                    modifier = Modifier.size(34.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.Edit,
                                        contentDescription = "Edit Service",
                                        tint = mutedGold,
                                        modifier = Modifier.size(22.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            containerColor = cardCream,
            title = {
                Text(
                    text = if (editingService == null) "Add New Service" else "Edit Service",
                    color = textDark,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    OutlinedTextField(
                        value = serviceNameInput,
                        onValueChange = { serviceNameInput = it },
                        label = { Text("Service Name") },
                        singleLine = true
                    )
                    OutlinedTextField(
                        value = serviceDurationInput,
                        onValueChange = { serviceDurationInput = it.filter { ch -> ch.isDigit() } },
                        label = { Text("Duration (mins)") },
                        singleLine = true
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        val trimmedName = serviceNameInput.trim()
                        val mins = serviceDurationInput.toIntOrNull() ?: 60
                        if (trimmedName.isNotEmpty()) {
                            val current = editingService
                            if (current == null) {
                                val nextId = (services.maxOfOrNull { it.id } ?: 0) + 1
                                services.add(ManagedServiceItem(nextId, trimmedName, mins, true))
                            } else {
                                val idx = services.indexOfFirst { it.id == current.id }
                                if (idx != -1) {
                                    services[idx] = current.copy(
                                        name = trimmedName,
                                        durationMinutes = mins
                                    )
                                }
                            }
                            showDialog = false
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = darkBrown)
                ) {
                    Text("Save", color = Color.White)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Cancel", color = textDark)
                }
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ManageServicesScreenPreview() {
    ManageServicesScreen()
}

