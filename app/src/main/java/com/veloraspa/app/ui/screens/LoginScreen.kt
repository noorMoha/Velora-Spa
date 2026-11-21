package com.veloraspa.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.veloraspa.app.ui.theme.VeloraspaTheme

enum class LoginRole {
    CUSTOMER,
    ADMIN_STAFF
}

@Composable
fun LoginScreen(
    onLoginSuccess: (LoginRole) -> Unit = {},
    onForgotPasswordClick: () -> Unit = {},
    onSignUpClick: () -> Unit = {},
    onAdminPortalClick: () -> Unit = {}
) {
    var selectedRole by remember { mutableStateOf(LoginRole.CUSTOMER) }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    val colorScheme = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.background)
            .padding(horizontal = 24.dp, vertical = 32.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // العنوان الرئيسي
            Text(
                text = "Welcome Back!",
                style = typography.titleLarge.copy(
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(18.dp))

            // شريط تبديل الدور (customer | Admin & Staff)
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(42.dp)
                    .clip(RoundedCornerShape(22.dp))
                    .background(colorScheme.primary)
                    .padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(18.dp))
                        .clickable { selectedRole = LoginRole.CUSTOMER },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "customer",
                        style = typography.bodyLarge.copy(
                            fontWeight = if (selectedRole == LoginRole.CUSTOMER) FontWeight.Bold else FontWeight.Medium,
                            fontSize = 14.sp
                        ),
                        color = if (selectedRole == LoginRole.CUSTOMER) {
                            colorScheme.onPrimary
                        } else {
                            colorScheme.onPrimary.copy(alpha = 0.75f)
                        }
                    )
                }

                Text(
                    text = "|",
                    style = typography.bodyLarge,
                    color = colorScheme.onPrimary.copy(alpha = 0.7f),
                    modifier = Modifier.padding(horizontal = 6.dp)
                )

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(18.dp))
                        .clickable { selectedRole = LoginRole.ADMIN_STAFF },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Admin & Staff",
                        style = typography.bodyLarge.copy(
                            fontWeight = if (selectedRole == LoginRole.ADMIN_STAFF) FontWeight.Bold else FontWeight.Medium,
                            fontSize = 14.sp
                        ),
                        color = if (selectedRole == LoginRole.ADMIN_STAFF) {
                            colorScheme.onPrimary
                        } else {
                            colorScheme.onPrimary.copy(alpha = 0.75f)
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(36.dp))

            // حقل البريد الإلكتروني
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = {
                    Text(
                        text = "Email Address",
                        style = typography.bodyLarge,
                        color = colorScheme.secondary
                    )
                },
                singleLine = true,
                shape = RoundedCornerShape(28.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedBorderColor = colorScheme.primary,
                    unfocusedBorderColor = colorScheme.primary,
                    focusedTextColor = colorScheme.onBackground,
                    unfocusedTextColor = colorScheme.onBackground,
                    cursorColor = colorScheme.primary
                ),
                modifier = Modifier
                    .fillMaxWidth(0.88f)
                    .height(54.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // حقل كلمة المرور
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = {
                    Text(
                        text = "Password",
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
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedBorderColor = colorScheme.primary,
                    unfocusedBorderColor = colorScheme.primary,
                    focusedTextColor = colorScheme.onBackground,
                    unfocusedTextColor = colorScheme.onBackground,
                    cursorColor = colorScheme.primary
                ),
                modifier = Modifier
                    .fillMaxWidth(0.88f)
                    .height(54.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // رابط نسيان كلمة المرور
            Box(
                modifier = Modifier.fillMaxWidth(0.88f),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(
                    text = "Forget password?",
                    style = typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    ),
                    color = colorScheme.onBackground,
                    modifier = Modifier
                        .clickable { onForgotPasswordClick() }
                        .padding(vertical = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(22.dp))

            // زر تسجيل الدخول
            Button(
                onClick = { onLoginSuccess(selectedRole) },
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
                    text = "Login",
                    style = typography.titleMedium.copy(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = colorScheme.onPrimary
                )
            }

            Spacer(modifier = Modifier.height(22.dp))

            // رابط إنشاء حساب جديد
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Don't have an account? ",
                    style = typography.bodyMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp
                    ),
                    color = colorScheme.onBackground
                )
                Text(
                    text = "Sign Up",
                    style = typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        textDecoration = TextDecoration.Underline
                    ),
                    color = colorScheme.onBackground,
                    modifier = Modifier.clickable { onSignUpClick() }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // رابط بوابة الأدمن
            Text(
                text = "Admin Portal Access",
                style = typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    textDecoration = TextDecoration.Underline
                ),
                color = colorScheme.onBackground,
                modifier = Modifier.clickable { onAdminPortalClick() }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    VeloraspaTheme {
        LoginScreen()
    }
}