package com.apps.uangku

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnalyticsScreen(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(top = 16.dp)
            .padding(horizontal = 16.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
            AnalyticsHeader()
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            TotalIncomeOutcomeCard()
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            PieChartCard()
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            CategoryDetailsa()
        }

        item {
            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}

@Composable
fun AnalyticsHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = Color.Gray
            )

            Column {
                Text(
                    "Monthly Analytics",
                    color = Color.Black,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    "August 2026",
                    color = Color.Gray,
                    fontSize = 12.sp,
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(end = 16.dp)
        ) {
            Icon(
                Icons.Default.Notifications,
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = Color.Black
            )
        }
    }
}

@Composable
fun TotalIncomeOutcomeCard() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SummaryCard(
            modifier = Modifier.weight(1f),
            title = "Income",
            amount = "Rp3.000.000,00",
            percentageText = "+10% Than Last Month",
            isPositive = true
        )
        SummaryCard(
            modifier = Modifier.weight(1f),
            title = "Outcome",
            amount = "Rp2.000.000,00",
            percentageText = "+15% Than Last Month",
            isPositive = false
        )
    }
}

data class ChartData (
    val title: String,
    val percentage: Float,
    val color: Color
)

@Composable
fun PieChartCard() {
    val dataList = listOf(
        ChartData(
            "Travel",
            40f,
            Color(0xFF7B7EFE)
        ),
        ChartData(
            "Shopping",
            20f,
            Color(0xFFE88235)
        ),
        ChartData(
            "Software",
            30f,
            Color(0xFF404B65)
        ),
        ChartData(
            "Other",
            10f,
            Color(0xFF2C3140)
        )
    )

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF080B44)
        )
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Expense Breakdown",
                color = Color.White.copy(alpha = 0.6f),
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(24.dp))

            DonutPieChart(data = dataList)

            Spacer(modifier = Modifier.height(32.dp))

            ChartLegend(data = dataList)
        }
    }
}

@Composable
fun DonutPieChart(data: List<ChartData>) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(200.dp)
    ) {
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            var startAngle = -90f
            val strokeWidth = 30.dp.toPx()

            data.forEach { item ->
                val sweepAngle = (item.percentage / 100f) * 360

                drawArc(
                    color = item.color,
                    startAngle = startAngle,
                    sweepAngle = sweepAngle,
                    useCenter = false,
                    style = Stroke(width = strokeWidth, cap = StrokeCap.Butt)
                )

                startAngle += sweepAngle
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Top Category",
                color = Color.Gray,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = data.maxByOrNull { it.percentage }?.title ?: "",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun ChartLegend(data: List<ChartData>) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(0.85f),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                LegendItem(item = data[0], modifier = Modifier.weight(1f))
                LegendItem(item = data[1], modifier = Modifier.weight(1f))
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                LegendItem(item = data[2], modifier = Modifier.weight(1f))
                LegendItem(item = data[3], modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun LegendItem(item: ChartData, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(10.dp)
                .background(
                    item.color,
                    CircleShape
                    )
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "${item.title} (${item.percentage.toInt()}%)",
            color = Color.White.copy(alpha = 0.8f),
            fontSize = 12.sp
        )
    }
}

@Composable
fun CategoryDetails() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Category Details",
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Row(
                modifier = Modifier
                    .clickable { /* See All logic */ }
                    .semantics{contentDescription = "SeeAllButtonCategoryDetails"},
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "See All",
                    color = Color.Gray,
                    fontSize = 14.sp,
                )
                Icon(
                    Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = null,
                    modifier = Modifier.size(14.dp),
                    tint = Color.Gray
                )
            }
        }
    }
}

@Composable
fun SummaryCard(
    modifier: Modifier = Modifier,
    title: String,
    amount: String,
    percentageText: String,
    isPositive: Boolean
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF080B44)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = title,
                color = Color.White.copy(alpha = 0.6f),
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = amount,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = percentageText,
                color = if (isPositive) Color(0xFF00FF00) else Color(0xFFFF0000),
                fontSize = 10.sp
            )
        }
    }
}

data class CategoryItemData(
    val title: String,
    val icon: ImageVector,
    val iconBgColor: Color,
    val percentage: Float,
    val amount: String,
    val progressColor: Color
)

@Composable
fun CategoryDetailsa(
    onSeeAllClick: () -> Unit = {}
) {
    val categories = listOf(
        CategoryItemData(
            title = "Food & Beverage",
            icon = Icons.Default.Fastfood,
            iconBgColor = Color(0xFFFFF3E0),
            percentage = 0.40f,
            amount = "Rp 1.200.000",
            progressColor = Color(0xFFFF9800)
        ),
        CategoryItemData(
            title = "Transportation",
            icon = Icons.Default.DirectionsCar,
            iconBgColor = Color(0xFFE3F2FD),
            percentage = 0.25f,
            amount = "Rp 750.000",
            progressColor = Color(0xFF2196F3)
        ),
        CategoryItemData(
            title = "Groceries",
            icon = Icons.Default.ShoppingCart,
            iconBgColor = Color(0xFFE8F5E9),
            percentage = 0.20f,
            amount = "Rp 600.000",
            progressColor = Color(0xFF4CAF50)
        ),
        CategoryItemData(
            title = "Entertainment",
            icon = Icons.Default.Movie,
            iconBgColor = Color(0xFFF3E5F5),
            percentage = 0.15f,
            amount = "Rp 450.000",
            progressColor = Color(0xFF9C27B0)
        )
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Category Details",
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Row(
                modifier = Modifier
                    .clickable { onSeeAllClick() }
                    .semantics { contentDescription = "SeeAllCategoryDetails" },
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

        Spacer(modifier = Modifier.height(12.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            categories.forEach { item ->
                CategoryDetailCard(data = item)
            }
        }
    }
}

@Composable
fun CategoryDetailCard(data: CategoryItemData) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF9FAFB)),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(data.iconBgColor),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = data.icon,
                        contentDescription = data.title,
                        tint = data.progressColor,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = data.title,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                    Text(
                        text = "${(data.percentage * 100).toInt()}% of total expenses",
                        fontSize = 11.sp,
                        color = Color.Gray
                    )
                }

                Text(
                    text = data.amount,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            LinearProgressIndicator(
                progress = { data.percentage },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(3.dp)),
                color = data.progressColor,
                trackColor = Color(0xFFE5E7EB)
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "spec:width=402dp,height=874dp",
    name = "Analytics Preview"
)
@Composable
fun AnalyticsHeaderPreview() {
    AnalyticsScreen()
}