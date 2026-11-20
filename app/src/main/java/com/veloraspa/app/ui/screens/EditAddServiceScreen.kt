package com.veloraspa.app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.veloraspa.app.R
import com.veloraspa.app.model.Service
import com.veloraspa.app.ui.theme.VeloraspaTheme
import com.veloraspa.app.ui.theme.DarkBrown
import com.veloraspa.app.ui.theme.SoftBrown
import com.veloraspa.app.ui.theme.CardBackground

@Composable
fun EditAddServiceScreen(
    service: Service? = null,
    onBackClick: () -> Unit = {},
    onSaveClick: () -> Unit = {},
    onPreviewClick: () -> Unit = {},
    onChangeImageClick: () -> Unit = {}
) {
    var serviceName by remember { mutableStateOf(service?.name ?: "Signature Massage") }
    var price by remember { mutableStateOf(service?.price?.toString() ?: "90") }
    var duration by remember { mutableStateOf(service?.duration ?: "60") }
    var category by remember { mutableStateOf(service?.category ?: "Massage Therapy") }
    var description by remember { mutableStateOf(service?.description ?: "Experience ultimate relaxation with our signature treatment, combining Swedish and deep tissue techniques.") }

    var isFeatured by remember { mutableStateOf(true) }
    var isActive by remember { mutableStateOf(true) }
    var selectedSpecialist by remember { mutableStateOf("Any Available Specialist") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier.size(40.dp)
                ) {
                    Text(
                        text = "←",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkBrown
                    )
                }

                Spacer(modifier = Modifier.width(72.dp))

                Text(
                    text = "Edit Service",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = DarkBrown
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(32.dp))
                    .background(CardBackground),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.massage),
                    contentDescription = "Service Image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                Button(
                    onClick = onChangeImageClick,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(12.dp)
                        .height(32.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp)
                ) {
                    Text(text = "Change Image", fontSize = 11.sp, color = DarkBrown, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))


            InputTextField(label = "Service Name", value = serviceName, onValueChange = { serviceName = it })

            Spacer(modifier = Modifier.height(12.dp))


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    InputTextField(label = "Price ($)", value = price, onValueChange = { price = it })
                }
                Box(modifier = Modifier.weight(1f)) {
                    InputTextField(label = "Duration (minutes):", value = duration, onValueChange = { duration = it })
                }
            }

            Spacer(modifier = Modifier.height(12.dp))


            InputTextField(label = "Category", value = category, onValueChange = { category = it })

            Spacer(modifier = Modifier.height(12.dp))


            Column(modifier = Modifier.fillMaxWidth()) {
                Text(text = "Description", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = DarkBrown, modifier = Modifier.padding(bottom = 4.dp))
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = CardBackground,
                        unfocusedContainerColor = CardBackground,
                        focusedBorderColor = SoftBrown,
                        unfocusedBorderColor = SoftBrown
                    ),
                    textStyle = LocalTextStyle.current.copy(fontSize = 13.sp, color = DarkBrown)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(text = "Featured Service", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = DarkBrown)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Switch(
                            checked = isFeatured,
                            onCheckedChange = { isFeatured = it },
                            colors = SwitchDefaults.colors(checkedThumbColor = SoftBrown, checkedTrackColor = CardBackground)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "Yes", fontSize = 12.sp, color = DarkBrown)
                    }
                }

                Column {
                    Text(text = "Available / Active", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = DarkBrown)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Switch(
                            checked = isActive,
                            onCheckedChange = { isActive = it },
                            colors = SwitchDefaults.colors(checkedThumbColor = SoftBrown, checkedTrackColor = CardBackground)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "Yes", fontSize = 12.sp, color = DarkBrown)
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))


            Column(modifier = Modifier.fillMaxWidth()) {
                Text(text = "Assign Specialist", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = DarkBrown, modifier = Modifier.padding(bottom = 4.dp))
                OutlinedTextField(
                    value = selectedSpecialist,
                    onValueChange = {},
                    readOnly = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = CardBackground,
                        unfocusedContainerColor = CardBackground,
                        focusedBorderColor = SoftBrown,
                        unfocusedBorderColor = SoftBrown
                    ),
                    trailingIcon = { Text(text = "▼", fontSize = 12.sp, color = DarkBrown, modifier = Modifier.padding(end = 12.dp)) },
                    textStyle = LocalTextStyle.current.copy(fontSize = 13.sp, color = DarkBrown)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))


            Button(
                onClick = onSaveClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp),
                shape = RoundedCornerShape(22.dp),
                colors = ButtonDefaults.buttonColors(containerColor = CardBackground, contentColor = DarkBrown)
            ) {
                Text(text = "Save Changes", fontSize = 14.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(8.dp))


            Button(
                onClick = onPreviewClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp),
                shape = RoundedCornerShape(22.dp),
                colors = ButtonDefaults.buttonColors(containerColor = CardBackground, contentColor = DarkBrown)
            ) {
                Text(text = "Preview", fontSize = 14.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun InputTextField(label: String, value: String, onValueChange: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = label, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = DarkBrown, modifier = Modifier.padding(bottom = 4.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(25.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = CardBackground,
                unfocusedContainerColor = CardBackground,
                focusedBorderColor = SoftBrown,
                unfocusedBorderColor = SoftBrown
            ),
            textStyle = LocalTextStyle.current.copy(fontSize = 13.sp, color = DarkBrown, fontWeight = FontWeight.Medium)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EditAddServicePreview() {
    VeloraspaTheme {
        EditAddServiceScreen()
    }
}