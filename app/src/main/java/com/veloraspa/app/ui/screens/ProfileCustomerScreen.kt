package com.veloraspa.app.ui.screens
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.veloraspa.app.ui.theme.*

@Composable
fun ProfileScreen(
    headerTitle: String = "Profile",
    userName: String = "User Profile",
    userHandle: String = "@Profile summary",
    nameValue: String = "Public",
    basicDetailsValue: String = "Summary",
    specialistValue: String = "Elena Bauufin",
    profileImageRes: Int? = null,
    onBackClick: () -> Unit = {},
    onCancelClick: () -> Unit = {},
    onSubmitClick: (rating: Int, feedback: String, showMyFeedback: Boolean) -> Unit = { _, _, _ -> }
) {
    var rating by remember { mutableIntStateOf(5) }
    var feedbackText by remember { mutableStateOf("") }
    var showMyFeedback by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PinkBackground)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // الهيدر العلوي مع زر الرجوع والصورة الشخصية
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = CardBackground,
                    shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
                )
                .padding(horizontal = 16.dp, vertical = 20.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(SoftBrown.copy(alpha = 0.55f))
                    .clickable { onBackClick() }
                    .align(Alignment.TopStart),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBackIosNew,
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = headerTitle,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkBrown
                )

                Spacer(modifier = Modifier.height(14.dp))

                if (profileImageRes != null) {
                    Image(
                        painter = painterResource(id = profileImageRes),
                        contentDescription = userName,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(72.dp)
                            .clip(CircleShape)
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .size(72.dp)
                            .clip(CircleShape)
                            .background(SoftBrown.copy(alpha = 0.25f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = userName.take(2).uppercase(),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = DarkBrown
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // اسم المستخدم والوصف المختصر
        Text(
            text = userName,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = DarkBrown,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = userHandle,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = SoftBrown,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(18.dp))

        // صف التفاصيل (يسار / يمين)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = "Name",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkBrown
                )
                Text(
                    text = "Basic details",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkBrown
                )
                Text(
                    text = "Specialist",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkBrown
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = nameValue,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkBrown
                )
                Text(
                    text = basicDetailsValue,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkBrown
                )
                Text(
                    text = specialistValue,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkBrown,
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // خط منقط أفقي
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .height(2.dp)
        ) {
            drawLine(
                color = DarkBrown,
                start = Offset(0f, size.height / 2f),
                end = Offset(size.width, size.height / 2f),
                strokeWidth = 3f,
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(5f, 6f), 0f)
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        // قسم التقييم والملاحظات
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            Text(
                text = "Leave Review",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = DarkBrown
            )

            Spacer(modifier = Modifier.height(10.dp))

            // نجوم التقييم الخمس
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                for (starIndex in 1..5) {
                    val isFilled = starIndex <= rating
                    Icon(
                        imageVector = if (isFilled) Icons.Filled.Star else Icons.Outlined.StarBorder,
                        contentDescription = "Star $starIndex",
                        tint = if (isFilled) StarYellow else SoftBrown,
                        modifier = Modifier
                            .size(40.dp)
                            .clickable { rating = starIndex }
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // صندوق كتابة التعليق
            OutlinedTextField(
                value = feedbackText,
                onValueChange = { feedbackText = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp),
                shape = RoundedCornerShape(12.dp),
                placeholder = {
                    Text(
                        text = "Text feedback.....",
                        color = DarkBrown.copy(alpha = 0.75f),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = CardBackground,
                    unfocusedContainerColor = CardBackground,
                    focusedBorderColor = SoftBrown,
                    unfocusedBorderColor = SoftBrown.copy(alpha = 0.5f),
                    focusedTextColor = DarkBrown,
                    unfocusedTextColor = DarkBrown,
                    cursorColor = DarkBrown
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            // خيار إظهار التعليق
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { showMyFeedback = !showMyFeedback },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = showMyFeedback,
                    onCheckedChange = { showMyFeedback = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = DarkBrown,
                        uncheckedColor = Color.White,
                        checkmarkColor = Color.White
                    )
                )
                Text(
                    text = "show my feedback",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkBrown
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // أزرار Cancel و Submit
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = onCancelClick,
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    shape = CircleShape,
                    border = BorderStroke(1.dp, SoftBrown.copy(alpha = 0.35f)),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = DarkBrown
                    )
                ) {
                    Text(
                        text = "Cancel",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkBrown
                    )
                }

                Button(
                    onClick = { onSubmitClick(rating, feedbackText, showMyFeedback) },
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = DarkBrown,
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = "Submit",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    VeloraspaTheme {
        ProfileScreen()
    }
}
