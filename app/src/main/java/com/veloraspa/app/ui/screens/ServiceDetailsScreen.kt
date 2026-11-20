package com.veloraspa.app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
fun ServiceDetailsScreen(
    service: Service,
    onBackClick: () -> Unit = {},
    onBookClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {}
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
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
                    modifier = Modifier.size(50.dp)
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
                    text = "Service Details",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = DarkBrown
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
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
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = service.name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkBrown
                )

                IconButton(
                    onClick = onFavoriteClick,
                    modifier = Modifier.size(40.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(text = "❤️", fontSize = 18.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "$${service.price} - ${service.duration}",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium,
                    color = SoftBrown
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = service.description,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 20.sp,
                color = DarkBrown,
                lineHeight = 24.sp
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = onBookClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = SoftBrown,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Book Now",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = onFavoriteClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = SoftBrown,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Favorite",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ServiceDetailsPreview() {
    VeloraspaTheme {
        val sampleService = Service(
            id = "1",
            name = "Signature Massage",
            category = "Massage",
            price = 90.0,
            duration = "60 min",
            description = "Experience ultimate relaxation with our signature treatment, combining Swedish and deep tissue techniques. Designed to relieve muscle tension, improve circulation, and restore balance using premium essential oils for a complete mind and body refresh."
        )
        ServiceDetailsScreen(service = sampleService)
    }
}