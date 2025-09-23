package com.teddybuddyaio.data

object FakeTeddyBuddyRepository {
    fun loadState(): TeddyBuddyUiState {
        val deviceStatus = DeviceStatus(
            serialNumber = "7CC267904C64",
            battery = 100,
            temperature = 30,
            storage = 50,
            isOnline = true
        )

        val coreFeatures = listOf(
            CoreFeature(
                title = "长时记忆",
                description = "记录Teddy Buddy与孩子的独特故事",
                route = "long_term_memory"
            ),
            CoreFeature(
                title = "情绪分析",
                description = "读取孩子的情绪曲线与趋势",
                route = "emotion"
            ),
            CoreFeature(
                title = "主题空间",
                description = "开启每周主题对话与学习",
                route = "topics"
            )
        )

        val featuredActions = listOf(
            CoreFeature(
                title = "家庭广播",
                description = "将语音消息发送给所有家庭成员"
            ),
            CoreFeature(
                title = "睡前故事",
                description = "根据心情挑选最舒缓的故事"
            ),
            CoreFeature(
                title = "成长手册",
                description = "生成孩子本周的成长报告"
            )
        )

        val routines = listOf(
            DeviceRoutine(
                title = "午睡模式",
                description = "自动调暗灯光并播放白噪音",
                isEnabled = true
            ),
            DeviceRoutine(
                title = "作业时间",
                description = "阻止分心应用并开启专注提醒",
                isEnabled = false
            ),
            DeviceRoutine(
                title = "早安问候",
                description = "每日7:30发送天气与课程提醒",
                isEnabled = true
            )
        )

        val trend = listOf(
            EmotionTrendPoint(
                label = "周一",
                values = mapOf(
                    EmotionCategory.Joy to 0.7f,
                    EmotionCategory.Calm to 0.6f,
                    EmotionCategory.Sadness to 0.2f,
                    EmotionCategory.Anger to 0.1f,
                    EmotionCategory.Boredom to 0.3f,
                    EmotionCategory.Fear to 0.2f
                )
            ),
            EmotionTrendPoint(
                label = "周二",
                values = mapOf(
                    EmotionCategory.Joy to 0.6f,
                    EmotionCategory.Calm to 0.7f,
                    EmotionCategory.Sadness to 0.3f,
                    EmotionCategory.Anger to 0.2f,
                    EmotionCategory.Boredom to 0.4f,
                    EmotionCategory.Fear to 0.3f
                )
            ),
            EmotionTrendPoint(
                label = "周三",
                values = mapOf(
                    EmotionCategory.Joy to 0.65f,
                    EmotionCategory.Calm to 0.5f,
                    EmotionCategory.Sadness to 0.25f,
                    EmotionCategory.Anger to 0.2f,
                    EmotionCategory.Boredom to 0.35f,
                    EmotionCategory.Fear to 0.25f
                )
            ),
            EmotionTrendPoint(
                label = "周四",
                values = mapOf(
                    EmotionCategory.Joy to 0.72f,
                    EmotionCategory.Calm to 0.58f,
                    EmotionCategory.Sadness to 0.18f,
                    EmotionCategory.Anger to 0.15f,
                    EmotionCategory.Boredom to 0.28f,
                    EmotionCategory.Fear to 0.22f
                )
            ),
            EmotionTrendPoint(
                label = "周五",
                values = mapOf(
                    EmotionCategory.Joy to 0.75f,
                    EmotionCategory.Calm to 0.63f,
                    EmotionCategory.Sadness to 0.2f,
                    EmotionCategory.Anger to 0.12f,
                    EmotionCategory.Boredom to 0.3f,
                    EmotionCategory.Fear to 0.18f
                )
            ),
            EmotionTrendPoint(
                label = "周六",
                values = mapOf(
                    EmotionCategory.Joy to 0.8f,
                    EmotionCategory.Calm to 0.7f,
                    EmotionCategory.Sadness to 0.18f,
                    EmotionCategory.Anger to 0.1f,
                    EmotionCategory.Boredom to 0.25f,
                    EmotionCategory.Fear to 0.15f
                )
            ),
            EmotionTrendPoint(
                label = "周日",
                values = mapOf(
                    EmotionCategory.Joy to 0.82f,
                    EmotionCategory.Calm to 0.74f,
                    EmotionCategory.Sadness to 0.15f,
                    EmotionCategory.Anger to 0.08f,
                    EmotionCategory.Boredom to 0.22f,
                    EmotionCategory.Fear to 0.14f
                )
            )
        )

        val distribution = mapOf(
            EmotionCategory.Joy to 32f,
            EmotionCategory.Calm to 26f,
            EmotionCategory.Sadness to 12f,
            EmotionCategory.Anger to 8f,
            EmotionCategory.Boredom to 12f,
            EmotionCategory.Fear to 10f
        )

        val emotionReport = EmotionReport(
            overview = "本周你的情绪相对平静，偶尔有一些烦躁，但也有一些开怀的时刻。",
            highlights = "当情绪值较平稳时，机器人的互动也更加顺畅。",
            suggestion = "建议继续做愉快的活动，转移注意力，降低烦恼情绪；安排一个亲子共读时间，让开心的感受延续更久。",
            trend = trend,
            distribution = distribution
        )

        return TeddyBuddyUiState(
            userName = "用户6981",
            greeting = "今天和 Teddy Buddy 一起度过美好时光吧",
            deviceStatus = deviceStatus,
            coreFeatures = coreFeatures,
            featuredActions = featuredActions,
            routines = routines,
            emotionReport = emotionReport
        )
    }
}
