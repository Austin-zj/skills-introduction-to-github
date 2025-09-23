package com.teddybuddyaio.ui.screens

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
import androidx.compose.material.icons.rounded.BatteryFull
import androidx.compose.material.icons.rounded.DeviceHub
import androidx.compose.material.icons.rounded.MonitorHeart
import androidx.compose.material.icons.rounded.SmartToy
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import com.teddybuddyaio.data.CoreFeature
import com.teddybuddyaio.data.DeviceRoutine
import com.teddybuddyaio.data.TeddyBuddyUiState
import com.teddybuddyaio.R
import com.teddybuddyaio.ui.theme.NeutralBackground
import com.teddybuddyaio.ui.theme.PrimaryBlue
import com.teddybuddyaio.ui.theme.SecondaryYellow
import com.teddybuddyaio.ui.theme.SurfaceWhite

@Composable
fun HomeScreen(
    uiState: TeddyBuddyUiState,
    onNavigateToEmotion: () -> Unit,
    onToggleRoutine: (String, Boolean) -> Unit
) {
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
                    text = "你好，${uiState.userName}",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = uiState.greeting,
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray),
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            item {
                DeviceStatusCard(
                    name = "Teddy Buddy",
                    status = uiState.deviceStatus
                )
            }

            item {
                SectionHeader(title = "核心功能")
                FeatureGrid(
                    features = uiState.coreFeatures,
                    onFeatureSelected = {
                        if (it.route == "emotion") {
                            onNavigateToEmotion()
                        }
                    }
                )
            }

            item {
                SectionHeader(title = "特色功能")
                FeatureList(features = uiState.featuredActions)
                AddSkillButton()
            }

            item {
                SectionHeader(title = "智能场景")
                RoutineList(routines = uiState.routines, onToggleRoutine = onToggleRoutine)
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@Composable
private fun DeviceStatusCard(name: String, status: com.teddybuddyaio.data.DeviceStatus) {
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .background(
                            brush = Brush.horizontalGradient(listOf(PrimaryBlue, PrimaryBlue.copy(alpha = 0.75f))),
                            shape = CircleShape
                        )
                        .padding(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.SmartToy,
                        contentDescription = null,
                        tint = SurfaceWhite
                    )
                }
                Column(modifier = Modifier.padding(start = 16.dp)) {
                    Text(text = name, style = MaterialTheme.typography.headlineSmall)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        StatusBadge(text = "SN: ${status.serialNumber}")
                        if (status.isOnline) {
                            StatusBadge(text = "在线", highlight = true, modifier = Modifier.padding(start = 8.dp))
                        }
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                MetricColumn(
                    icon = Icons.Rounded.BatteryFull,
                    label = "电量",
                    value = "${status.battery}%"
                )
                MetricColumn(
                    icon = Icons.Rounded.MonitorHeart,
                    label = "温度",
                    value = "${status.temperature}°C"
                )
                MetricColumn(
                    icon = Icons.Rounded.DeviceHub,
                    label = "存储",
                    value = "${status.storage}%"
                )
            }
        }
    }
}

@Composable
private fun SectionHeader(title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title, style = MaterialTheme.typography.headlineSmall)
        TextButton(onClick = { }) {
            Text(text = "查看全部", color = PrimaryBlue)
        }
    }
}

@Composable
private fun FeatureGrid(
    features: List<CoreFeature>,
    onFeatureSelected: (CoreFeature) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        features.chunked(2).forEach { rowFeatures ->
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxWidth()) {
                rowFeatures.forEach { feature ->
                    ElevatedCard(
                        onClick = { onFeatureSelected(feature) },
                        modifier = Modifier
                            .weight(1f)
                            .height(120.dp),
                        colors = CardDefaults.elevatedCardColors(containerColor = SurfaceWhite)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = feature.title, style = MaterialTheme.typography.headlineSmall)
                            Text(text = feature.description, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
                        }
                    }
                }
                if (rowFeatures.size == 1) {
                    Spacer(
                        modifier = Modifier
                            .weight(1f)
                            .height(120.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun FeatureList(features: List<CoreFeature>) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        features.forEach { feature ->
            ElevatedCard(
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.elevatedCardColors(containerColor = SurfaceWhite),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = feature.title, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.SemiBold)
                    Text(
                        text = feature.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray,
                        modifier = Modifier.padding(top = 6.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun AddSkillButton(onClick: () -> Unit = {}) {
    ElevatedCard(
        onClick = onClick,
        colors = CardDefaults.elevatedCardColors(containerColor = PrimaryBlue.copy(alpha = 0.12f)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        shape = RoundedCornerShape(18.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(id = R.string.new_skill), color = PrimaryBlue, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Composable
private fun RoutineList(
    routines: List<DeviceRoutine>,
    onToggleRoutine: (String, Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(SurfaceWhite, shape = RoundedCornerShape(18.dp))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        routines.forEachIndexed { index, routine ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = routine.title, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.SemiBold)
                    Text(text = routine.description, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
                }
                Switch(
                    checked = routine.isEnabled,
                    onCheckedChange = {
                        onToggleRoutine(routine.title, it)
                    }
                )
            }
            if (index != routines.lastIndex) {
                Divider(color = NeutralBackground)
            }
        }
    }
}

@Composable
private fun StatusBadge(text: String, highlight: Boolean = false, modifier: Modifier = Modifier) {
    ElevatedAssistChip(
        onClick = {},
        label = { Text(text = text, color = if (highlight) PrimaryBlue else Color.DarkGray) },
        modifier = modifier,
        colors = AssistChipDefaults.elevatedAssistChipColors(
            containerColor = if (highlight) SecondaryYellow.copy(alpha = 0.3f) else NeutralBackground
        )
    )
}

@Composable
private fun MetricColumn(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .background(PrimaryBlue.copy(alpha = 0.08f), shape = CircleShape)
                .padding(12.dp)
        ) {
            Icon(imageVector = icon, contentDescription = null, tint = PrimaryBlue)
        }
        Text(text = value, style = MaterialTheme.typography.headlineSmall, modifier = Modifier.padding(top = 8.dp))
        Text(text = label, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
    }
}
