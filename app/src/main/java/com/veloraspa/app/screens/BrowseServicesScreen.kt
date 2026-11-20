package com.veloraspa.app.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.veloraspa.app.data.MockDataSource
import com.veloraspa.app.model.Service
import com.veloraspa.app.ui.theme.VeloraspaTheme

private val SpaRoseBackground = Color(0xFFF6E8EA)
private val SpaCardCream = Color(0xFFECE3D2)
private val SpaChipUnselected = Color(0xFFF3E2DC)
private val SpaEspressoPrimary = Color(0xFF5A3825)
private val SpaInkDark = Color(0xFF231F20)
private val SpaInkMuted = Color(0xFF5A524E)

@Composable
fun BrowseServicesScreen(
    modifier: Modifier = Modifier,
    services: List<Service> = MockDataSource.servicesList,
    onBackClick: () -> Unit = {},
    onBookService: (Service) -> Unit = {}
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("All") }
    var bookmarkedIds by remember { mutableStateOf(setOf<String>()) }

    val categories = remember(services) {
        val dynamicCategories = services.map { it.category }.distinct()
        listOf("All") + dynamicCategories
    }

    val filteredServices = remember(services, searchQuery, selectedCategory) {
        services.filter { service ->
            val matchesCategory = selectedCategory == "All" ||
                    service.category.equals(selectedCategory, ignoreCase = true)
            val matchesQuery = searchQuery.isBlank() ||
                    service.name.contains(searchQuery, ignoreCase = true) ||
                    service.description.contains(searchQuery, ignoreCase = true) ||
                    service.category.contains(searchQuery, ignoreCase = true)
            matchesCategory && matchesQuery
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(SpaRoseBackground)
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        // Top Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = SpaInkDark
                )
            }
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Browse Services",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = SpaInkDark
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            singleLine = true,
            shape = RoundedCornerShape(8.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = SpaInkDark
                )
            },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = { searchQuery = "" }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Clear search",
                            tint = SpaInkDark
                        )
                    }
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedBorderColor = SpaInkDark,
                unfocusedBorderColor = SpaInkDark.copy(alpha = 0.7f),
                focusedTextColor = SpaInkDark,
                unfocusedTextColor = SpaInkDark,
                cursorColor = SpaEspressoPrimary
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Categories Section
        Text(
            text = "Categories",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = SpaInkDark
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(end = 8.dp)
        ) {
            items(categories) { category ->
                val isSelected = selectedCategory == category
                FilterChip(
                    selected = isSelected,
                    onClick = { selectedCategory = category },
                    label = {
                        Text(
                            text = category,
                            fontSize = 12.sp,
                            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    shape = RoundedCornerShape(10.dp),
                    border = if (isSelected) null else BorderStroke(1.dp, SpaChipUnselected),
                    colors = FilterChipDefaults.filterChipColors(
                        containerColor = SpaChipUnselected,
                        labelColor = SpaInkDark,
                        selectedContainerColor = SpaEspressoPrimary,
                        selectedLabelColor = Color.White
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Services List
        if (filteredServices.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No matching services found.",
                    fontSize = 14.sp,
                    color = SpaInkMuted
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                items(filteredServices, key = { it.id }) { service ->
                    val isBookmarked = service.id in bookmarkedIds
                    ServiceBrowseCard(
                        service = service,
                        isBookmarked = isBookmarked,
                        onToggleBookmark = {
                            bookmarkedIds = if (isBookmarked) {
                                bookmarkedIds - service.id
                            } else {
                                bookmarkedIds + service.id
                            }
                        },
                        onBookClick = { onBookService(service) }
                    )
                }
            }
        }
    }
}

@Composable
private fun ServiceBrowseCard(
    service: Service,
    isBookmarked: Boolean,
    onToggleBookmark: () -> Unit,
    onBookClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(4.dp),
        border = BorderStroke(1.dp, SpaInkDark.copy(alpha = 0.55f)),
        colors = CardDefaults.cardColors(containerColor = SpaCardCream)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Service Visual Placeholder
            Box(
                modifier = Modifier
                    .size(width = 112.dp, height = 96.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(SpaChipUnselected),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = service.name,
                    tint = SpaEspressoPrimary,
                    modifier = Modifier.size(34.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Service Info + Bookmark + Book Now
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = service.name,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = SpaInkDark,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )

                    IconButton(
                        onClick = onToggleBookmark,
                        modifier = Modifier.size(28.dp)
                    ) {
                        Icon(
                            imageVector = if (isBookmarked) {
                                Icons.Default.Favorite
                            } else {
                                Icons.Default.FavoriteBorder
                            },
                            contentDescription = "Bookmark service",
                            tint = SpaInkDark,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }

                Text(
                    text = "${service.description} (${service.duration} • $${service.price.toInt()})",
                    fontSize = 11.sp,
                    lineHeight = 14.sp,
                    color = SpaInkMuted,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        onClick = onBookClick,
                        shape = RoundedCornerShape(10.dp),
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp),
                        modifier = Modifier.height(30.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = SpaEspressoPrimary,
                            contentColor = Color.White
                        )
                    ) {
                        Text(
                            text = "Book Now",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun BrowseServicesScreenPreview() {
    VeloraspaTheme {
        BrowseServicesScreen()
    }
}