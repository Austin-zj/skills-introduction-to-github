package com.teddybuddyaio.data

import androidx.compose.ui.graphics.Color
import com.teddybuddyaio.ui.theme.AngryColor
import com.teddybuddyaio.ui.theme.BoredColor
import com.teddybuddyaio.ui.theme.CalmColor
import com.teddybuddyaio.ui.theme.FearColor
import com.teddybuddyaio.ui.theme.JoyColor
import com.teddybuddyaio.ui.theme.SadColor

/**
 * Defines the emotional dimensions that the Teddy Buddy companion tracks.
 */
enum class EmotionCategory(val displayName: String, val color: Color) {
    Joy("快乐", JoyColor),
    Calm("平静", CalmColor),
    Sadness("失落", SadColor),
    Anger("烦躁", AngryColor),
    Boredom("无聊", BoredColor),
    Fear("担忧", FearColor)
}

data class DeviceStatus(
    val serialNumber: String,
    val battery: Int,
    val temperature: Int,
    val storage: Int,
    val isOnline: Boolean
)

data class CoreFeature(
    val title: String,
    val description: String,
    val route: String? = null
)

data class DeviceRoutine(
    val title: String,
    val description: String,
    val isEnabled: Boolean
)

data class EmotionTrendPoint(
    val label: String,
    val values: Map<EmotionCategory, Float>
)

data class EmotionReport(
    val overview: String,
    val highlights: String,
    val suggestion: String,
    val trend: List<EmotionTrendPoint>,
    val distribution: Map<EmotionCategory, Float>
)

data class TeddyBuddyUiState(
    val userName: String,
    val greeting: String,
    val deviceStatus: DeviceStatus,
    val coreFeatures: List<CoreFeature>,
    val featuredActions: List<CoreFeature>,
    val routines: List<DeviceRoutine>,
    val emotionReport: EmotionReport
)
