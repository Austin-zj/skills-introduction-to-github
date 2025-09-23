package com.teddybuddyaio.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Insights
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.teddybuddyaio.data.EmotionCategory
import com.teddybuddyaio.data.EmotionReport
import com.teddybuddyaio.data.EmotionTrendPoint
import com.teddybuddyaio.ui.theme.NeutralBackground
import com.teddybuddyaio.ui.theme.PrimaryBlue
import com.teddybuddyaio.ui.theme.SurfaceWhite

@Composable
fun EmotionAnalysisScreen(report: EmotionReport) {
    val categories = remember { EmotionCategory.values().toList() }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(NeutralBackground)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = "情绪分析",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "理解孩子的情绪波动，打造更懂你的AI陪伴",
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray),
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            item {
                SummaryCard(report)
            }

            item {
                TrendCard(categories = categories, points = report.trend)
            }

            item {
                DistributionCard(categories = categories, distribution = report.distribution)
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@Composable
private fun SummaryCard(report: EmotionReport) {
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .background(
                            brush = Brush.linearGradient(listOf(PrimaryBlue, PrimaryBlue.copy(alpha = 0.6f))),
                            shape = CircleShape
                        )
                        .padding(12.dp)
                ) {
                    androidx.compose.material3.Icon(
                        imageVector = Icons.Rounded.Insights,
                        contentDescription = null,
                        tint = SurfaceWhite
                    )
                }
                Text(
                    text = "本周情绪总结",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }

            SummaryItem(title = "总体状态", content = report.overview)
            SummaryItem(title = "亮点捕捉", content = report.highlights)
            SummaryItem(title = "陪伴建议", content = report.suggestion)
        }
    }
}

@Composable
private fun SummaryItem(title: String, content: String) {
    Column(modifier = Modifier.padding(top = 16.dp)) {
        Text(text = title, style = MaterialTheme.typography.labelLarge, color = PrimaryBlue)
        Text(
            text = content,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(top = 6.dp)
        )
    }
}

@Composable
private fun TrendCard(categories: List<EmotionCategory>, points: List<EmotionTrendPoint>) {
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = "情绪曲线",
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = "多维度追踪一周内的情绪变化",
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
            )
            EmotionTrendChart(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .padding(top = 16.dp),
                categories = categories,
                points = points
            )
            EmotionLegend(categories = categories)
        }
    }
}

@Composable
private fun EmotionLegend(categories: List<EmotionCategory>) {
    Column(modifier = Modifier.padding(top = 12.dp)) {
        categories.chunked(3).forEach { rowItems ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                rowItems.forEach { category ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .background(category.color, CircleShape)
                                .padding(6.dp)
                        )
                        Text(
                            text = category.displayName,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun DistributionCard(
    categories: List<EmotionCategory>,
    distribution: Map<EmotionCategory, Float>
) {
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = "情绪分布",
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = "当前积累的情绪能量比例",
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
            )
            EmotionDistributionChart(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
                    .padding(top = 16.dp),
                distribution = distribution
            )
            EmotionLegend(categories = categories)
        }
    }
}

@Composable
private fun EmotionTrendChart(
    modifier: Modifier = Modifier,
    categories: List<EmotionCategory>,
    points: List<EmotionTrendPoint>
) {
    val maxValue = remember(points) {
        points.flatMap { point ->
            categories.map { category -> point.values[category] ?: 0f }
        }.maxOrNull()?.coerceAtLeast(0.1f) ?: 1f
    }
    val ySteps = 4
    Column(modifier = modifier) {
        Canvas(modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
        ) {
            val chartHeight = size.height
            val chartWidth = size.width
            val stepX = if (points.size > 1) chartWidth / (points.size - 1) else chartWidth

            for (i in 0..ySteps) {
                val y = chartHeight - chartHeight / ySteps * i
                drawLine(
                    color = NeutralBackground,
                    start = Offset(0f, y),
                    end = Offset(chartWidth, y)
                )
            }

            drawLine(color = NeutralBackground, start = Offset(0f, 0f), end = Offset(0f, chartHeight))

            categories.forEach { category ->
                val path = Path()
                val pointsForCategory = points.mapIndexed { index, point ->
                    val value = point.values[category] ?: 0f
                    val ratio = value / maxValue
                    val x = stepX * index
                    val y = chartHeight - chartHeight * ratio
                    Offset(x, y)
                }

                pointsForCategory.forEachIndexed { index, offset ->
                    if (index == 0) {
                        path.moveTo(offset.x, offset.y)
                    } else {
                        path.lineTo(offset.x, offset.y)
                    }
                }

                drawPath(
                    path = path,
                    color = category.color,
                    style = Stroke(width = 6f, cap = StrokeCap.Round, join = StrokeJoin.Round)
                )

                pointsForCategory.forEach { pointOffset ->
                    drawCircle(color = category.color, radius = 8f, center = pointOffset)
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            points.forEach { point ->
                Text(
                    text = point.label,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
private fun EmotionDistributionChart(
    modifier: Modifier = Modifier,
    distribution: Map<EmotionCategory, Float>,
    stroke: Dp = 28.dp
) {
    val total = distribution.values.sum().coerceAtLeast(1f)
    Canvas(modifier = modifier) {
        val strokeWidth = stroke.toPx()
        val diameter = size.minDimension - strokeWidth
        val topLeft = Offset((size.width - diameter) / 2f, (size.height - diameter) / 2f)
        var startAngle = -90f
        distribution.forEach { (category, value) ->
            val sweep = (value / total) * 360f
            drawArc(
                color = category.color,
                startAngle = startAngle,
                sweepAngle = sweep,
                useCenter = false,
                topLeft = topLeft,
                size = Size(diameter, diameter),
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
            )
            startAngle += sweep
        }
    }
}
