package com.apps.uangku

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import kotlinx.coroutines.launch
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apps.uangku.ui.theme.UangKuTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        window.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE
        )

        setContent {
            UangKuTheme {
                    MainDashboardScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainDashboardScreen() {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    var selectedBottomNavIndex by remember { mutableIntStateOf(0) }

    var showSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    Scaffold(
        bottomBar = {
            CustomBottomNavigationBar(
                selectedItem = selectedBottomNavIndex,
                onItemSelected = { selectedBottomNavIndex = it }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showSheet = true },
                shape = CircleShape,
                containerColor = Color(0xFF6200EE),
                contentColor = Color.White,
                modifier = Modifier
                    .size(60.dp)
                    .offset(y = 50.dp)
                    .semantics{ contentDescription = "FabAdd" }
            ) {
                Icon(Icons.Default.Add,
                    contentDescription = "Add",
                    modifier = Modifier.size(32.dp)
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { innerPadding ->
        if (showSheet) {
            ModalBottomSheet(
                onDismissRequest = { showSheet = false },
                sheetState = sheetState,
                containerColor = Color.White
            ) {
                ProviderSelectionContent(
                    onProviderSelected = { _ ->
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                showSheet = false
                            }
                        }
                    }
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (selectedBottomNavIndex) {
                0 -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White)
                            .padding(horizontal = 16.dp)
                            .padding(top = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        item {
                            MainHeader()
                        }

                        item {
                            Spacer(modifier = Modifier.height(8.dp))
                        }

                        item {
                            TotalBalanceCard()
                        }

                        item {
                            Spacer(modifier = Modifier.height(8.dp))
                        }

                        item {
                            WalletListSection(
                                onSeeAllClicked = {
                                    selectedBottomNavIndex = 2
                                }
                            )
                        }

                        item {
                            Spacer(modifier = Modifier.height(8.dp))
                        }

                        item {
                            TransactionHeader(
                                selectedTabIndex = selectedTabIndex,
                                onTabSelected = { selectedTabIndex = it }
                            )
                        }

                        when (selectedTabIndex) {
                            0 -> {
                                items(6) { index ->
                                    TransactionItem(
                                        icon = if (index % 2 == 0) Icons.Default.Videocam else Icons.Default.ShoppingCart,
                                        iconColor = if (index % 2 == 0) Color(0xFFA855F7) else Color(0xFF10B981),
                                        title = if (index % 2 == 0) "Netflix" else "Vegetables",
                                        category = if (index % 2 == 0) "Streaming" else "Groceries",
                                        amount = if (index % 2 == 0) "-RP300.000,00" else "-RP90.000,00",
                                        account = if (index % 2 == 0) "GoPay" else "BCA",
                                        time = "Today 12.54 PM"
                                    )
                                }
                            }
                            1 -> {
                                items(6) { index ->
                                    TransactionItem(
                                        icon = if (index % 2 == 0) Icons.Default.Payments else Icons.Default.CardGiftcard,
                                        iconColor = if (index % 2 == 0) Color(0xFF10B981) else Color(0xFFFFB020),
                                        title = if (index % 2 == 0) "Salary" else "Gift",
                                        category = if (index % 2 == 0) "Monthly" else "Reward",
                                        amount = if (index % 2 == 0) "+RP15.000.000,00" else "+RP500.000,00",
                                        account = if (index % 2 == 0) "Bank" else "Wallet",
                                        time = "Today 09.00 AM"
                                    )
                                }
                            }
                            2 -> {
                                items(6) { index ->
                                    TransactionItem(
                                        icon = if (index % 2 == 0) Icons.Default.Savings else Icons.Default.DirectionsCar,
                                        iconColor = if (index % 2 == 0) Color(0xFF3B82F6) else Color(0xFF6366F1),
                                        title = if (index % 2 == 0) "Emergency Fund" else "New Car",
                                        category = if (index % 2 == 0) "Security" else "Dream",
                                        amount = if (index % 2 == 0) "RP1.000.000,00" else "RP5.000.000,00",
                                        account = if (index % 2 == 0) "Bank" else "Investment",
                                        time = "Ongoing"
                                    )
                                }
                            }
                        }
                        item {
                            Spacer(modifier = Modifier.height(80.dp))
                        }
                    }
                }
                1 -> {
                    AnalyticsScreen(
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
                2 -> {
                    WalletPage()
                }
                3 -> {
                    ProfilePage()
                }
            }
        }
    }
}

@Composable
fun MainHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Default.Person,
                contentDescription = "Profile",
                tint = Color.Black,
                modifier = Modifier.size(24.dp)
            )

            Column {
                Text(
                    "Welcome back,",
                    color = Color.Black,
                    fontSize = 8.sp,
                    lineHeight = 10.sp
                )
                Text(
                    "Muhammad Fikri",
                    color = Color.Black,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 12.sp
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Default.Notifications,
                contentDescription = "Notifications",
                tint = Color.Black,
                modifier = Modifier.size(24.dp)
            )
            Icon(
                Icons.Default.Settings,
                contentDescription = "Settings",
                tint = Color.Black,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun TransactionItem(
    icon: ImageVector,
    iconColor: Color,
    title: String,
    category: String,
    amount: String,
    account: String,
    time: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .semantics{contentDescription = "TransactionItem$title"},
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.3f)),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(iconColor.copy(alpha = 0.2f), RoundedCornerShape(16.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        icon,
                        contentDescription = null,
                        tint = iconColor,
                        modifier = Modifier.size(24.dp)
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                    Text(
                        text = category,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }

            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = amount,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = if (amount.startsWith("-")) Color(0xFFEF4444) else Color(0xFF10B981)
                )
                Text(
                    text = "$account · $time",
                    fontSize = 11.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun TransactionHeader(
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit
) {
    val tabs = listOf("Expenses", "Income", "Savings")

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Transactions",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black
            )
            Row(
                modifier = Modifier
                    .clickable { /* See All logic */ }
                    .semantics{contentDescription = "SeeAllButton"},
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("See All", color = Color.Gray, fontSize = 14.sp)
                Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(16.dp))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF3F4F6), RoundedCornerShape(12.dp))
                .padding(4.dp)
                .selectableGroup(),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            tabs.forEachIndexed { index, title ->
                val isSelected = selectedTabIndex == index
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(8.dp))
                        .background(if (isSelected) Color.White else Color.Transparent)
                        .semantics{ contentDescription = "TransactionTab$title" }
                        .selectable(
                            selected = isSelected,
                            onClick = { onTabSelected(index) },
                            role = Role.Tab
                        )
                        .padding(vertical = 8.dp)
                        .semantics{contentDescription = "TransactionTab$title"},
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = title,
                        color = if (isSelected) Color.Black else Color.Gray,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}

@Composable
fun WalletSection(walletName:String, number:String) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF06034B)),
        modifier = Modifier
            .height(140.dp)
            .width(220.dp)
            .semantics{contentDescription = "WalletCard$walletName"}
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        Icons.Default.AccountBalanceWallet,
                        contentDescription = null,
                        tint = Color.White.copy(alpha = 0.6f),
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        walletName,
                        color = Color.White.copy(alpha = 0.8f),
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .background(Color.White.copy(alpha = 0.2f),
                            RoundedCornerShape(12.dp))
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .background(Color.Green, CircleShape)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        "Synced",
                        color = Color.Green,
                        fontSize = 10.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                number,
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 14.sp,
                letterSpacing = 7.sp
            )
            Column {
                Text(
                    "Balance",
                    color = Color.White.copy(alpha = 0.5f),
                    fontSize = 12.sp
                )
                Text(
                    "Rp300.000.000,00",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun WalletListSection(
    onSeeAllClicked: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "My Wallet",
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Row(
                modifier = Modifier
                    .clickable { onSeeAllClicked() }
                    .semantics { contentDescription = "SeeAllButton" },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "See All",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(18.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            item {
                WalletSection("Gopay", "0812-8291-9092")
            }

            item {
                WalletSection("Dana", "0812-8291-9092")
            }

            item {
                WalletSection("Bank BCA", "725-8102-9921")
            }
        }
    }
}

@Composable
fun TotalBalanceCard() {
    Card(
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF06034B)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(24.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Total Balance", color = Color.White.copy(alpha = 0.8f))

                Box(
                    modifier = Modifier
                        .background(Color.White.copy(alpha = 0.2f), RoundedCornerShape(15.dp))
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    Text("August 2026", color = Color.White, fontSize = 12.sp)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Rp300.000.000,00",
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.semantics{contentDescription = "TotalBalanceText"}
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    Icons.Default.Visibility,
                    contentDescription = null,
                    tint = Color.White.copy(alpha = 0.6f),
                    modifier = Modifier.semantics{contentDescription = "ToggleVisibilityIcon"}
                )
            }
            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                BalancePill("Rp30.000.000,00", color = Color(0xFF10B981), modifier = Modifier.weight(1f))
                BalancePill("Rp30.000.000,00", color = Color(0xFFEF4444), modifier = Modifier.weight(1f))
                BalancePill("Rp30.000.000,00", color = Color(0xFFA855F7), modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun BalancePill(
    amount: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(color.copy(alpha = 0.1f), CircleShape)
            .border(
                1.dp,
                color.copy(alpha = 0.5f),
                CircleShape
            )
            .padding(
                horizontal = 6.dp,
                vertical = 6.dp
            )
    ) {
        Text(
            text = amount,
            color = color,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun CustomBottomNavigationBar(selectedItem: Int, onItemSelected: (Int) -> Unit) {
    Surface(
        color = Color.White,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color.LightGray.copy(alpha = 0.3f))
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp, horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BottomNavItem(Icons.Default.Home, "Home", selectedItem == 0) { onItemSelected(0) }
                BottomNavItem(Icons.Default.Analytics, "Analytics", selectedItem == 1) { onItemSelected(1) }

                Spacer(modifier = Modifier.width(60.dp))

                BottomNavItem(Icons.Default.AccountBalanceWallet, "Wallet", selectedItem == 2) { onItemSelected(2) }
                BottomNavItem(Icons.Default.Person, "Profile", selectedItem == 3) { onItemSelected(3) }
            }
        }
    }
}

@Composable
fun BottomNavItem(icon: ImageVector, label: String, isSelected: Boolean, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .semantics{contentDescription = "BottomNavItem$label"}
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = if (isSelected) Color(0xFF6200EE) else Color.Gray,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            color = if (isSelected) Color(0xFF6200EE) else Color.Gray
        )
    }
}

@Preview(showBackground = true,
         showSystemUi = true,
         device = "spec:width=402dp,height=874dp",
         name = "Main Preview")
@Composable
fun MainDashboardScreenPreview() {
    UangKuTheme {
        MainDashboardScreen()
    }
}