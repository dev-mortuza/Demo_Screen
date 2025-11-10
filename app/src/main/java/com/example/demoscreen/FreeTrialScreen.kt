package com.example.demoscreen

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults.contentWindowInsets
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.demoscreen.ui.theme.MontserratFontFamily

@Composable
fun FreeTrialScreen() {
    FreeTrialScreenContent(
        onStartTrialClick = {},
        onCloseClick = {}
    )
}



@Composable
private fun FreeTrialScreenContent(
    onStartTrialClick: () -> Unit = {},
    onCloseClick: () -> Unit = {}
) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    val scrollState = rememberScrollState()

    val topSectionHeightPercent = if (isLandscape) 0.95f else 0.42f
    val screenHeightDp = configuration.screenHeightDp.dp
    val topSectionHeight = screenHeightDp * topSectionHeightPercent
    val cardOverlapOffset = if (isLandscape) (screenHeightDp * 0.25f) else (screenHeightDp * 0.13f)

    val gradient = Brush.linearGradient(
        colors = listOf(
            colorResource(id = R.color.gradient_start),
            colorResource(id = R.color.gradient_end)
        )
    )

    val gradientTop = Brush.linearGradient(
        colors = listOf(
            colorResource(id = R.color.gradient_start).copy(alpha = 0.9f),
            colorResource(id = R.color.gradient_end).copy(alpha = 0.9f)
        )
    )

    Scaffold {paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF9F9F9))
                .padding(WindowInsets.navigationBars.asPaddingValues())
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .verticalScroll(scrollState)
            ) {
                // 🌈 Top gradient section
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(topSectionHeight)
                        .zIndex(0f)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_header_box_background),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.matchParentSize()
                    )

                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .background(gradientTop)
                    ) {
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

                        // ✨ Title + Icon
                        Column(
                            modifier = Modifier.align(Alignment.Center),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_upgrade_btn),
                                contentDescription = null,
                                tint = Color.Yellow,
                                modifier = Modifier.size(48.dp)
                            )
                            Text(
                                text = stringResource(R.string.free_trial_screen_title),
                                color = Color.Yellow,
                                fontSize = 24.sp,
                                fontFamily = FontFamily(Font(R.font.montserrat_bold)),
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }
                    }
                }

                // 💎 Floating Card
                FeatureCard(
                    isLandscape = false,
                    cardOverlapOffset = cardOverlapOffset
                )

                Spacer(modifier = Modifier.height(cardOverlapOffset - screenHeightDp * 0.20f))

                // ⚪ Bottom section
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(horizontal = 20.dp, vertical = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        text = stringResource(R.string.trial_notice_text),
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.Medium,
                            fontFamily = MontserratFontFamily,
                            fontSize = 14.sp,
                            color = Color.DarkGray,
                            textAlign = TextAlign.Center
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // 🚀 Upgrade Button
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(54.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(gradient),
                        contentAlignment = Alignment.Center
                    ) {
                        Button(
                            onClick = onStartTrialClick,
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
                            Text(
                                text = stringResource(R.string.free_trial_screen_btn_label),
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

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = stringResource(R.string.free_trial_screen_billing_description),
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Normal,
                            fontFamily = MontserratFontFamily,
                            fontSize = 11.sp,
                            color = Color.DarkGray,
                            lineHeight = 16.sp,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }
            }
        }
    }
}





@Preview(showSystemUi = true)
@Composable
fun ShowFreeTrial() {
    FreeTrialScreen()
}
