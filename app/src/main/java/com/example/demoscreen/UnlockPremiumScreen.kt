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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex

@Composable
fun UnlockPremiumScreen() {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    val scrollState = rememberScrollState() // ⬅️ Scroll State

    // Calculate the height of the top section in DP
    val topSectionHeightPercent = if (isLandscape) 0.95f else 0.42f
    val screenHeightDp = configuration.screenHeightDp.dp
    val topSectionHeight = screenHeightDp * topSectionHeightPercent

    // The amount the Card will be offset upwards to overlap the top section
    // 60.dp is a common value for visually centering the card on the boundary
    val cardOverlapOffset = if(isLandscape) (screenHeightDp*0.25f) else (screenHeightDp*0.13f)


    val gradient = Brush.linearGradient(
        colors = listOf(Color(0xFF695AF7), Color(0xFFCF56C9))
    )

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9F9F9))
            .padding(WindowInsets.navigationBars.asPaddingValues())
    ){
        Column( // ⬅️ Changed from Box to Column
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .verticalScroll(scrollState) // ⬅️ Made Scrollable
            // Removed WindowInsets padding here, as it's often best on the Surface or top level Column
        ) {
            // 🌈 Top gradient section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(topSectionHeight) // ⬅️ Fixed Height
                    .zIndex(0f) // Keep behind the card
            ) {
                Image(
                    painter = painterResource(id = R.drawable.header),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.matchParentSize()
                )

                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .background(
                            Brush.linearGradient(
                                listOf(Color(0xE5695AF7), Color(0xF0CF56C9))
                            )
                        )
                ) {
                    // Offer Closing Button
                    IconButton(
                        onClick = { /* close */ },
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

                    // Title + Icon
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
                            text = "Unlock Premium",
                            color = Color.Yellow,
                            fontSize = 24.sp,
                            fontFamily = FontFamily(Font(R.font.montserrat_bold)),
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
            }

            // 💎 Floating Card Container (Achieves Overlap)
            FeatureCard(
                isLandscape = false,
                cardOverlapOffset = cardOverlapOffset
            )

            // ⬇️ Compensation Spacer
            // This pushes the bottom content down, compensating for the card's upward offset.
            Spacer(modifier = Modifier.height(cardOverlapOffset - screenHeightDp*0.20f))

            // ⚪ Bottom section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    // Removed align(Alignment.BottomCenter)
                    .background(Color.White)
                    .padding(horizontal = 24.dp, vertical = 28.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top // Changed from Arrangement.Bottom
            ) {
                Text(
                    text = "FREE 7-day trial, then USD \$29.90/year.",
                    color = Color(0xFF616161),
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily(Font(R.font.montserrat_medium))
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Upgrade Button
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(gradient),
                    contentAlignment = Alignment.Center
                ) {
                    ElevatedButton(
                        onClick = { /* handle upgrade */ },
                        colors = ButtonDefaults.elevatedButtonColors(
                            containerColor = Color.Transparent,
                            contentColor = Color.White
                        ),
                        elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 0.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(54.dp),
                        shape = RoundedCornerShape(18.dp),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp, vertical = 16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Continue",
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center,
                                fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
                                color = Color.White
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    Text(
                        text = "Recurrent billing. Cancel anytime. You will be\ncharged after trial ends unless canceled.",
                        color = Color(0xFF616161),
                        fontSize = 11.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                        lineHeight = 16.sp
                    )
                }
            }
        }
    }
}




@Preview(showSystemUi = true)
@Composable
fun ShowUnlockPremiumScreen() {
    UnlockPremiumScreen()
}
