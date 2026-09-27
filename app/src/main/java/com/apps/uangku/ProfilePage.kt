package com.apps.uangku

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfilePage(modifier: Modifier = Modifier) {
    var biometricsEnabled by remember { mutableStateOf(true) }
    var autoLockEnabled by remember { mutableStateOf(false) }
    var darkModeEnabled by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item { Spacer(modifier = Modifier.height(16.dp)) }

        item { ProfileHeader() }

        item {
            SecuritySection(
                biometricsEnabled = biometricsEnabled,
                onBiometricsChange = { biometricsEnabled = it },
                autoLockEnabled = autoLockEnabled,
                onAutoLockChange = { autoLockEnabled = it }
            )
        }

        item {
            PreferencesSection(
                darkModeEnabled = darkModeEnabled,
                onDarkModeChange = { darkModeEnabled = it }
            )
        }

        item { DataManagementSection() }

        item { Spacer(modifier = Modifier.height(8.dp)) }

        item { LogoutButton() }

        item { Spacer(modifier = Modifier.height(80.dp)) }
    }
}

@Composable
fun ProfileHeader() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(contentAlignment = Alignment.BottomEnd) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFF0F0F0)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile",
                    modifier = Modifier.size(60.dp),
                    tint = Color.LightGray
                )
            }
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF6200EE))
                    .clickable { },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Profile",
                    tint = Color.White,
                    modifier = Modifier.size(14.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Eleanor Vance",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(4.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "ID: EX-849201",
                fontSize = 12.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.width(8.dp))
            Surface(
                color = Color(0xFFE8EAF6),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Verified,
                        contentDescription = "Verified",
                        tint = Color(0xFF6200EE),
                        modifier = Modifier.size(12.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "VERIFIED ACCOUNT",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF6200EE)
                    )
                }
            }
        }
    }
}

@Composable
fun SecuritySection(
    biometricsEnabled: Boolean,
    onBiometricsChange: (Boolean) -> Unit,
    autoLockEnabled: Boolean,
    onAutoLockChange: (Boolean) -> Unit
) {
    SettingsCard(title = "Security") {
        SettingsSwitchItem(
            icon = Icons.Default.Fingerprint,
            title = "Biometrics",
            subtitle = "FaceID / Fingerprint",
            isChecked = biometricsEnabled,
            onCheckedChange = onBiometricsChange
        )
        SettingsSwitchItem(
            icon = Icons.Default.Timer,
            title = "Auto-Lock",
            subtitle = "Require authentication after 5 mins",
            isChecked = autoLockEnabled,
            onCheckedChange = onAutoLockChange
        )
        SettingsClickableItem(
            icon = Icons.Default.Password,
            title = "Change PIN"
        )
    }
}

@Composable
fun PreferencesSection(
    darkModeEnabled: Boolean,
    onDarkModeChange: (Boolean) -> Unit
) {
    SettingsCard(title = "Preferences") {
        SettingsClickableItem(
            icon = Icons.Default.MonetizationOn,
            title = "Currency",
            subtitle = "IDR (Indonesian Rupiah)"
        )
        SettingsSwitchItem(
            icon = Icons.Default.DarkMode,
            title = "Dark Mode",
            subtitle = "System default",
            isChecked = darkModeEnabled,
            onCheckedChange = onDarkModeChange
        )
    }
}

@Composable
fun DataManagementSection() {
    SettingsCard(title = "Data Management") {
        SettingsClickableItem(
            icon = Icons.Default.FileDownload,
            title = "Export Data",
            subtitle = "Download transaction history (CSV)"
        )
        SettingsClickableItem(
            icon = Icons.Default.Delete,
            title = "Delete History",
            subtitle = "Permanently remove local data",
            titleColor = Color(0xFFE53935),
            iconColor = Color(0xFFE53935)
        )
    }
}

@Composable
fun LogoutButton() {
    Button(
        onClick = { },
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFF0F0))
    ) {
        Icon(
            imageVector = Icons.Default.ExitToApp,
            contentDescription = "Logout",
            tint = Color(0xFFE53935)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Logout Securely",
            color = Color(0xFFE53935),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
    }
}

@Composable
fun SettingsCard(title: String, content: @Composable () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF9FAFB)),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(16.dp))
            content()
        }
    }
}

@Composable
fun SettingsSwitchItem(
    icon: ImageVector,
    title: String,
    subtitle: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.Gray,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = title, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
            Text(text = subtitle, fontSize = 12.sp, color = Color.Gray)
        }
        Switch(
            checked = isChecked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = Color(0xFF6200EE)
            )
        )
    }
}

@Composable
fun SettingsClickableItem(
    icon: ImageVector,
    title: String,
    subtitle: String? = null,
    titleColor: Color = Color.Black,
    iconColor: Color = Color.Gray
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = iconColor,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = title, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = titleColor)
            if (subtitle != null) {
                Text(text = subtitle, fontSize = 12.sp, color = Color.Gray)
            }
        }
        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = "Go",
            tint = Color.LightGray
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "spec:width=402dp,height=874dp"
)
@Composable
fun ProfilePagePreview() {
    ProfilePage()
}