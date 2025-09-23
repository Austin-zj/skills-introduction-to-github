package com.teddybuddyaio.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.SmartToy
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.teddybuddyaio.ui.theme.NeutralBackground
import com.teddybuddyaio.ui.theme.PrimaryBlue
import com.teddybuddyaio.ui.theme.SecondaryYellow
import com.teddybuddyaio.ui.theme.SurfaceWhite

@Composable
fun WelcomeScreen(
    onJoinClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(NeutralBackground)
            .padding(horizontal = 24.dp, vertical = 32.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "WELCOME, FRIEND!",
                style = MaterialTheme.typography.headlineSmall,
                color = PrimaryBlue,
                modifier = Modifier.fillMaxWidth()
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp),
                colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .height(160.dp)
                            .fillMaxWidth()
                            .background(
                                brush = Brush.radialGradient(
                                    colors = listOf(PrimaryBlue.copy(alpha = 0.25f), SurfaceWhite),
                                    radius = 220f
                                ),
                                shape = CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.SmartToy,
                            contentDescription = null,
                            tint = PrimaryBlue,
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(Color.White)
                                .padding(24.dp)
                        )
                    }

                    Text(
                        text = "YOUR AI COMPANION",
                        style = MaterialTheme.typography.labelLarge,
                        color = PrimaryBlue,
                        modifier = Modifier.padding(top = 16.dp)
                    )

                    Text(
                        text = "AI泰迪系列产品用户系统",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(top = 8.dp)
                    )

                    Text(
                        text = "长记忆陪伴 · 情绪分析 · 主题对话",
                        style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray),
                        modifier = Modifier.padding(top = 8.dp)
                    )

                    Button(
                        onClick = onJoinClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue),
                        contentPadding = PaddingValues(vertical = 14.dp)
                    ) {
                        Text(text = "立即加入", fontSize = 18.sp)
                    }

                    Button(
                        onClick = onLoginClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = SecondaryYellow, contentColor = Color(0xFF513C06)),
                        contentPadding = PaddingValues(vertical = 14.dp)
                    ) {
                        Text(text = "手机验证码登录/注册", fontWeight = FontWeight.SemiBold)
                    }
                }
            }

            Text(
                text = "YOUR AI COMPANION 每一天",
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
            )
        }
    }
}
