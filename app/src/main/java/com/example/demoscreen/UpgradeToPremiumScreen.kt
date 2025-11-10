package com.example.demoscreen


import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.demoscreen.ui.theme.MontserratFontFamily

@Composable
fun UpgradeToPremiumScreen() {
    UpgradeToPremiumScreenContent(
        onCloseClick = {},
        onUpgradeClick = {}
    )
}

@Composable
fun UpgradeToPremiumScreenContent(
    onCloseClick: () -> Unit,
    onUpgradeClick: () -> Unit
) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    val screenHeightDp = configuration.screenHeightDp.dp
    val topBoxHeight = if (isLandscape) (screenHeightDp * 0.50f) else screenHeightDp * 0.25f

    val gradient = Brush.linearGradient(
        colors = listOf(
            colorResource(id = R.color.gradient_start),
            colorResource(id = R.color.gradient_end)
        )
    )

    var selectedPlan by remember { mutableStateOf("yearly") }

    // Whole screen scrollable
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9F9F9))
            .padding(WindowInsets.navigationBars.asPaddingValues())
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(Color(0xFFFFFFFF))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(topBoxHeight)
                    .clip(BottomArcShape(arcHeightDp = 35f))
            ) {
                // 🖼️ Background image
                Image(
                    painter = painterResource(id = R.drawable.img_header_box_background),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .matchParentSize()
                        .graphicsLayer { translationY = -5f }
                )

                // 🌈 Gradient overlay
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    Color(0xFF695AF7).copy(alpha = 0.92f),
                                    Color(0xFFCF56C9).copy(alpha = 0.92f)
                                )
                            )
                        )
                )

                // ❌ Close Button
                IconButton(
                    onClick = onCloseClick,
                    modifier = Modifier
                        .padding(top = 55.dp, end = 24.dp)
                        .size(32.dp)
                        .align(Alignment.TopEnd)
                        .clip(CircleShape),
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.White
                    )
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_offer_close),
                        contentDescription = "Close",
                        tint = Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                }

                // 📝 Title text
                Text(
                    text = stringResource(R.string.upgrade_screen_title),
                    color = Color.White,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Spacer(Modifier.height(10.dp))

            FeatureCard(
                isLandscape = false,
                extraContent = { RatingWithAvatars() }
            )

            Spacer(Modifier.height(15.dp))

            // 📦 Plans
            PlanOption(
                title = "Monthly Plan",
                price = "$4.99",
                selected = selectedPlan == "monthly",
                onSelect = { selectedPlan = "monthly" }
            )

            Spacer(Modifier.height(10.dp))

            PlanOption(
                title = "Yearly Plan",
                price = "$29.90",
                tag = "Popular",
                offer = "50% OFF",
                selected = selectedPlan == "yearly",
                onSelect = { selectedPlan = "yearly" }
            )

            Spacer(Modifier.height(15.dp))

            // 🚀 Upgrade Button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .height(54.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(brush = gradient),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = onUpgradeClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                    shape = RoundedCornerShape(18.dp),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Box(
                            modifier = Modifier.size(24.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_upgrade_btn),
                                contentDescription = "Upgrade button icon",
                                tint = Color.Unspecified,
                                modifier = Modifier.size(22.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = stringResource(R.string.upgrade_screen_btn_label),
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontSize = 16.sp,
                                color = Color.White,
                                fontFamily = MontserratFontFamily,
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center
                            )
                        )
                    }
                }
            }

            Spacer(Modifier.height(10.dp))

            // 🧾 Billing Description
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.upgrade_screen_billing_description),
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Normal,
                        fontFamily = MontserratFontFamily,
                        fontSize = 11.sp,
                        color = Color.DarkGray,
                        lineHeight = 16.sp,
                        textAlign = TextAlign.Center
                    )
                )
            }

            Spacer(Modifier.height(10.dp))
        }
    }
}



@Composable
@Preview(showSystemUi = true)
fun ShowPremiumScreen() {
    UpgradeToPremiumScreen()
}