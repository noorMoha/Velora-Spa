package com.veloraspa.app.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material.icons.outlined.WorkOutline
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.veloraspa.app.ui.theme.*

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun StaffProfileScreen(
    staffName: String = "Sophia Chen",
    staffRole: String = "Senior Esthetician",
    ratingText: String = "3.9 (93 reviews)",
    experienceText: String = "6+ Years Experience",
    aboutText: String = "Sophia is a passionate and certified esthetician with over 6 years of experience in facial and skincare treatments. She believes in a personalized approach to beauty and wellness, helping each client feel relaxed, confident, and radiant.",
    specialties: List<String> = listOf(
        "Facial Treatment",
        "Skin Rejuvenation",
        "Anti-Aging",
        "Relaxation Massage",
        "Body Scrub",
        "Acne Care"
    ),
    workingHours: String = "Mon - Sat, 9:00 AM - 6:00 PM",
    phone: String = "+966 5437 68321",
    email: String = "sophia@veloraspa.com",
    languages: String = "English, Arabic",
    profileImageRes: Int? = null,
    onBackClick: () -> Unit = {},
    onViewScheduleClick: () -> Unit = {},
    onEditProfileClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PinkBackground)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 18.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // الهيدر العلوي
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(CardBackground)
                    .border(2.dp, DarkBrown, CircleShape)
                    .clickable { onBackClick() }
                    .align(Alignment.CenterStart),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = "Back",
                    tint = DarkBrown,
                    modifier = Modifier.size(20.dp)
                )
            }

            Text(
                text = "Staff Profile",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = SoftBrown,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        // قسم الصورة والبيانات الأساسية
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (profileImageRes != null) {
                Image(
                    painter = painterResource(id = profileImageRes),
                    contentDescription = staffName,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(116.dp)
                        .clip(CircleShape)
                        .border(3.dp, SoftBrown, CircleShape)
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(116.dp)
                        .clip(CircleShape)
                        .background(CardBackground)
                        .border(3.dp, SoftBrown, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = staffName.take(2).uppercase(),
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkBrown
                    )
                }
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = staffName,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Medium,
                    color = DarkBrown
                )
                Text(
                    text = staffRole,
                    fontSize = 14.sp,
                    color = DarkBrown
                )

                // شارة التقييم
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = CardBackground,
                    border = BorderStroke(1.dp, SoftBrown.copy(alpha = 0.5f))
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = "Rating",
                            tint = StarYellow,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = ratingText,
                            fontSize = 12.sp,
                            color = DarkBrown
                        )
                    }
                }

                // شارة سنوات الخبرة
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = CardBackground,
                    border = BorderStroke(1.dp, SoftBrown.copy(alpha = 0.5f))
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.WorkOutline,
                            contentDescription = "Experience",
                            tint = SoftBrown,
                            modifier = Modifier.size(15.dp)
                        )
                        Text(
                            text = experienceText,
                            fontSize = 12.sp,
                            color = DarkBrown
                        )
                    }
                }
            }
        }

        // بطاقة About
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = CardBackground),
            border = BorderStroke(2.dp, DarkBrown)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp, vertical = 14.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = "About",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = DarkBrown
                )
                Text(
                    text = aboutText,
                    fontSize = 12.5.sp,
                    lineHeight = 17.sp,
                    color = DarkBrown
                )
            }
        }

        // بطاقة Specialties & Services
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = CardBackground),
            border = BorderStroke(2.dp, DarkBrown)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp, vertical = 14.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "Specialties & Services",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = DarkBrown
                )

                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    specialties.forEach { specialty ->
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = SoftBrown.copy(alpha = 0.55f)
                        ) {
                            Text(
                                text = specialty,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium,
                                color = DarkBrown,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                            )
                        }
                    }
                }
            }
        }


        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = CardBackground),
            border = BorderStroke(2.dp, DarkBrown)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp, vertical = 14.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Schedule & Contact",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = DarkBrown
                )

                ContactDetailRow(
                    icon = Icons.Outlined.Schedule,
                    label = "Working Hours: ",
                    value = workingHours
                )
                ContactDetailRow(
                    icon = Icons.Outlined.Phone,
                    label = "Phone: ",
                    value = phone
                )
                ContactDetailRow(
                    icon = Icons.Outlined.Email,
                    label = "Email: ",
                    value = email
                )
                ContactDetailRow(
                    icon = Icons.Outlined.Language,
                    label = "Languages: ",
                    value = languages
                )
            }
        }

        // أزرار View Schedule و Edit Profile
        OutlinedButton(
            onClick = onViewScheduleClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp),
            shape = CircleShape,
            border = BorderStroke(2.dp, DarkBrown),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = CardBackground,
                contentColor = DarkBrown
            )
        ) {
            Text(
                text = "View Schedule",
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
        }

        OutlinedButton(
            onClick = onEditProfileClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp),
            shape = CircleShape,
            border = BorderStroke(2.dp, DarkBrown),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = CardBackground,
                contentColor = DarkBrown
            )
        ) {
            Text(
                text = "Edit Profile",
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun ContactDetailRow(
    icon: ImageVector,
    label: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = SoftBrown,
            modifier = Modifier.size(18.dp)
        )
        Text(
            text = "$label$value",
            fontSize = 12.sp,
            color = DarkBrown
        )
    }
}

@Preview(showBackground = true)
@Composable
fun StaffProfileScreenPreview() {
    VeloraspaTheme {
        StaffProfileScreen()
    }
}
