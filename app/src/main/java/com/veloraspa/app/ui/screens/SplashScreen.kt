package com.veloraspa.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SplashScreen(
    onGetStartedClick: () -> Unit = {},
    onLoginClick: () -> Unit = {}
) {
    // الألوان المستوحاة من التصميم (خلفية وردية فاتحة، زر وردي، ونصوص داكنة متناسقة)
    val backgroundColor = Color(0xFFF9F1F2)
    val buttonColor = Color(0xFFC88A8E)
    val textColor = Color(0xFF4A3B3C)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // عنوان التطبيق
            Text(
                text = "Velora Spa",
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                color = textColor
            )

            Spacer(modifier = Modifier.height(10.dp))

            // الوصف الترحيبي تحت العنوان
            Text(
                text = "Your sanctuary of peace\nand wellness.",
                fontSize = 15.sp,
                textAlign = TextAlign.Center,
                color = textColor.copy(alpha = 0.7f)
            )

            Spacer(modifier = Modifier.height(50.dp))

            // زر البدء (Get Started)
            Button(
                onClick = onGetStartedClick,
                colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(50.dp)
            ) {
                Text(
                    text = "Get Started",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // رابط تسجيل الدخول في الأسفل
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Already have an account? ",
                    fontSize = 13.sp,
                    color = textColor.copy(alpha = 0.7f)
                )
                TextButton(
                    onClick = onLoginClick,
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(
                        text = "Log in",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = textColor,
                        textDecoration = TextDecoration.Underline
                    )
                }
            }
        }
    }
}
