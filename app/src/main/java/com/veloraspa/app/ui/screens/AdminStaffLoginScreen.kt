package com.veloraspa.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.veloraspa.app.ui.theme.VeloraspaTheme

@Composable
fun AdminStaffLoginScreen(
    onSwitchToCustomer: () -> Unit = {},
    onSignInAsStaff: () -> Unit = {},
    onContactHrClick: () -> Unit = {}
) {
    var employeeEmailOrId by remember { mutableStateOf("") }
    var accessPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    val colorScheme = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography

    val fieldColors = OutlinedTextFieldDefaults.colors(
        focusedContainerColor = Color.White,
        unfocusedContainerColor = Color.White,
        focusedBorderColor = colorScheme.primary,
        unfocusedBorderColor = colorScheme.primary,
        focusedTextColor = colorScheme.onBackground,
        unfocusedTextColor = colorScheme.onBackground,
        cursorColor = colorScheme.primary
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.background)
            .padding(horizontal = 24.dp, vertical = 28.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // شريط التبديل العلوي: Customer (غير نشط) | Admin & Staff (نشط)
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.92f)
                    .height(48.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(colorScheme.surface)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clickable { onSwitchToCustomer() },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Customer",
                        style = typography.titleMedium.copy(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        color = colorScheme.primary
                    )
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(24.dp))
                        .background(colorScheme.primary),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Admin & Staff",
                        style = typography.titleMedium.copy(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        color = colorScheme.onPrimary
                    )
                }
            }

            Spacer(modifier = Modifier.height(64.dp))

            // العنوان الرئيسي والوصف الفرعي
            Text(
                text = "Manager &\nStaff Portal",
                style = typography.titleLarge.copy(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 36.sp
                ),
                textAlign = TextAlign.Center,
                color = colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Authorized access only",
                style = typography.bodyMedium.copy(
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium
                ),
                textAlign = TextAlign.Center,
                color = colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(44.dp))

            // حقل البريد الوظيفي أو الرقم الوظيفي
            OutlinedTextField(
                value = employeeEmailOrId,
                onValueChange = { employeeEmailOrId = it },
                placeholder = {
                    Text(
                        text = "Employee Email/ ID",
                        style = typography.bodyLarge,
                        color = colorScheme.secondary
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                shape = RoundedCornerShape(28.dp),
                colors = fieldColors,
                modifier = Modifier
                    .fillMaxWidth(0.88f)
                    .height(54.dp)
            )

            Spacer(modifier = Modifier.height(18.dp))

            // حقل كلمة مرور الدخول
            OutlinedTextField(
                value = accessPassword,
                onValueChange = { accessPassword = it },
                placeholder = {
                    Text(
                        text = "Access Password",
                        style = typography.bodyLarge,
                        color = colorScheme.secondary
                    )
                },
                singleLine = true,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val icon = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    val description = if (passwordVisible) "Hide password" else "Show password"
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = icon,
                            contentDescription = description,
                            tint = colorScheme.onBackground
                        )
                    }
                },
                shape = RoundedCornerShape(28.dp),
                colors = fieldColors,
                modifier = Modifier
                    .fillMaxWidth(0.88f)
                    .height(54.dp)
            )

            Spacer(modifier = Modifier.height(34.dp))

            // زر تسجيل الدخول كموظف/إداري
            Button(
                onClick = onSignInAsStaff,
                shape = RoundedCornerShape(26.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorScheme.primary,
                    contentColor = colorScheme.onPrimary
                ),
                modifier = Modifier
                    .fillMaxWidth(0.76f)
                    .height(50.dp)
            ) {
                Text(
                    text = "Sign in as Staff",
                    style = typography.titleMedium.copy(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = colorScheme.onPrimary
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // رابط التواصل مع HR عند نسيان بيانات الدخول
            val hrText = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = colorScheme.onBackground,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp
                    )
                ) {
                    append("Forgot staff credentials? ")
                }
                withStyle(
                    style = SpanStyle(
                        color = colorScheme.onBackground,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        textDecoration = TextDecoration.Underline
                    )
                ) {
                    append("Contact HR")
                }
            }

            Text(
                text = hrText,
                modifier = Modifier.clickable { onContactHrClick() }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AdminStaffLoginScreenPreview() {
    VeloraspaTheme {
        AdminStaffLoginScreen()
    }
}