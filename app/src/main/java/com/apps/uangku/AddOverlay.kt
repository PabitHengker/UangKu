package com.apps.uangku

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProviderSelectionContent(onProviderSelected: (String) -> Unit) {
    val providers = listOf(
        Triple("Bank BCA", Color(0xFF4C68D7), Icons.Default.AccountBalance),
        Triple("Bank Mandiri", Color(0xFF2B3990), Icons.Default.AccountBalance),
        Triple("Bank BNI", Color(0xFFF17C37), Icons.Default.AccountBalance),
        Triple("Gopay", Color(0xFF4CB3E6), Icons.Default.AccountBalance),
        Triple("OVO", Color(0xFF7B2CBF), Icons.Default.AccountBalance)
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .padding(bottom = 16.dp)
    ) {
        Text(
            text = "Select Provider",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.semantics { contentDescription = "ProviderSelectionTitle" }
        )
        Text(
            text = "Connect new bank or e-wallet to syncronize mutation automatically",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            modifier = Modifier
                .padding(top = 8.dp, bottom = 24.dp)
                .semantics { contentDescription = "ProviderSelectionDescription" }
        )

        providers.forEach { (name, color, icon) ->
            ProviderItem(name, color, icon) { 
                onProviderSelected(name) 
            }
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
fun ProviderItem(name: String, color: Color, icon: ImageVector, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .semantics { contentDescription = "ProviderItem$name" },
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.3f)),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .background(color, RoundedCornerShape(10.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon, 
                    contentDescription = null, 
                    tint = Color.White, 
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = name, 
                fontWeight = FontWeight.Bold, 
                fontSize = 18.sp,
                color = Color.Black
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProviderSelectionPreview() {
    Surface(color = Color.White) {
        ProviderSelectionContent(onProviderSelected = {})
    }
}
