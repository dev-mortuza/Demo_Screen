package com.example.demoscreen


import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.demoscreen.ui.theme.BottomArcShape

@Composable
fun UpgradeToPremiumScreen() {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    val screenHeightDp = configuration.screenHeightDp.dp
    val topBoxHeight = if (isLandscape) (screenHeightDp * 0.50f) else screenHeightDp*0.25f

    val gradient = Brush.linearGradient(
        colors = listOf(Color(0xFF695AF7), Color(0xFFCF56C9))
    )

    var selectedPlan by remember { mutableStateOf("yearly") }

    // Whole screen scrollable
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9F9F9))
            .padding(WindowInsets.navigationBars.asPaddingValues()) // ⬅️ important
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
//                    .clip(RoundedCornerShape(bottomStart = 97.dp, bottomEnd = 97.dp))
            ) {
                // 🖼️ Background image
                Image(
                    painter = painterResource(id = R.drawable.header),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .matchParentSize()
                        .graphicsLayer {
                            translationY = -5f  // move image upward visually (negative = up)
                        }
                )

                // 🌈 Your main gradient overlay (unchanged)
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
                // Close Button
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

                // 📝 Title text
                Text(
                    text = "Upgrade to premium",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.montserrat_bold)),
                    modifier = Modifier.align(Alignment.Center)
                )
            }



            Spacer(Modifier.height(20.dp))

            // ✅ Content padding below header
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                // Feature list
                Card(
                    modifier = Modifier.fillMaxWidth()
                        .shadow(elevation = 4.dp, shape = RoundedCornerShape(16.dp)),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(Modifier.padding(16.dp)) {
                        // 🧩 Feature list with individual icons
                        val features = listOf(
                            "Ai on Your Keyboard" to R.drawable.ic_feature_1,
                            "Smart Auto Reply" to R.drawable.ic_feature_2,
                            "Seamless AI Voice-to-Text" to R.drawable.ic_feature_3,
                            "Instant Tone Shift & Rewriter" to R.drawable.ic_feature_4,
                            "Flawless Grammar & Spell Fix" to R.drawable.ic_feature_5,
                            "Multi-Language Translation" to R.drawable.ic_feature_6,
                            "Ad-Free Experience" to R.drawable.ic_feature_7
                        )

                        features.forEach { (feature, iconRes) ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(24.dp)
                                        .clip(CircleShape),
//                                        .background(Color(0xFF8A3FFC).copy(alpha = 0.15f)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        painter = painterResource(id = iconRes),
                                        contentDescription = feature,
                                        tint = Color.Unspecified, // Important!
                                        modifier = Modifier.size(24.dp)
                                    )
                                }

                                Spacer(Modifier.width(10.dp))

                                Text(
                                    text = feature,
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                                    color = Color(0xFF374151)
                                )
                            }
                        }
                    }
                    val avatarBackgrounds = listOf(
                        Color(0xFFE0E0E0),
                        Color(0xFFD6E2C0),
                        Color(0xFFFFECB3),
                        Color(0xFFBBDEFB)
                    )
                    // Four different avatar drawables
                    val avatars = listOf(
                        R.drawable.img_user_1,
                        R.drawable.img_user_2,
                        R.drawable.img_user_3,
                        R.drawable.img_user_4
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 8.dp)
                            .padding(vertical = 4.dp)
                    ) {
                        // ⭐ Rating Stars
                        repeat(5) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_feedback_star), // your star drawable
                                contentDescription = "Star",
                                tint = Color.Unspecified,
                                modifier = Modifier.size(24.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        // 👤 Overlapping Avatars
                        Box {
                            avatars.forEachIndexed { index, avatarRes ->
                                Box(
                                    modifier = Modifier
                                        .size(36.dp)
                                        .offset(x = (index * 24).dp) // overlap
                                        .clip(CircleShape)
                                        .background(avatarBackgrounds[index])
                                ) {
                                    Image(
                                        painter = painterResource(id = avatarRes),
                                        contentDescription = "Avatar $index",
                                        modifier = Modifier
                                            .size(32.dp)
                                            .align(Alignment.Center)
                                    )
                                }
                            }
                        }
                    }
                }

                }

                Spacer(Modifier.height(20.dp))

                // Plans
                PlanOption(
                    title = "Monthly Plan",
                    price = "$4.99",
                    selected = selectedPlan == "monthly",
                    onSelect = { selectedPlan = "monthly" }
                )

                Spacer(Modifier.height(12.dp))

                PlanOption(
                    title = "Yearly Plan",
                    price = "$29.90",
                    tag = "Popular",
                    offer = "50% OFF",
                    selected = selectedPlan == "yearly",
                    onSelect = { selectedPlan = "yearly" }
                )

                Spacer(Modifier.height(20.dp))

                // Upgrade Button
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .height(54.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(brush = gradient),
                    contentAlignment = Alignment.Center
                ) {
                    ElevatedButton(
                        onClick = { /* handle upgrade */ },
                        colors = ButtonDefaults.elevatedButtonColors(
                            containerColor = Color.Transparent, // use transparent to show gradient
                            contentColor = Color.White
                        ),
                        elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 0.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(54.dp),
                        shape = RoundedCornerShape(18.dp),
                        contentPadding = PaddingValues(0.dp) // remove default inner padding
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
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
                                    tint = Color.Unspecified, // Important!
                                    modifier = Modifier.size(22.dp)
                                )

                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Upgrade Now",
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center,
                                fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
                                color = Color.White
                            )
                        }

                    }
                }


                Spacer(Modifier.height(16.dp))

                Text(
                    text = "Recurrent billing. Cancel anytime in Google Play.\nYou will be charged at purchase confirmation.",
                    color = Color(0xFF616161),
                    fontSize = 11.sp,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                    modifier = Modifier.fillMaxWidth(),
                    lineHeight = 16.sp
                )

                Spacer(Modifier.height(10.dp))
            }
        }
    }



@Composable
fun PlanOption(
    title: String,
    price: String,
    tag: String? = null,
    offer: String? = null,
    selected: Boolean,
    onSelect: () -> Unit
) {
    val gradientBrush = Brush.linearGradient(
        colors = listOf(
            Color(0xFF695AF7),
            Color(0xFFCF56C9)
        )
    )
// Border
    val borderBrush = if (selected) gradientBrush else SolidColor(Color(0xFFFFFFFF))

// Text color (use gradient when selected)
    val textColorBrush = if (selected) gradientBrush else SolidColor(Color.Black)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Wrap card and discount badge in BoxWithConstraints
        BoxWithConstraints(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            // responsive tag sizing
            val tagWidth = maxWidth * 0.20f
            val tagHeight = tagWidth * 0.25f

            // clamp max size to prevent overlap
            val finalTagWidth = tagWidth.coerceAtMost(80.dp)
            val finalTagHeight = tagHeight.coerceAtMost(20.dp)

            // Inner Box allows align()
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                // --- Main Card ---
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .border(1.dp, borderBrush, RoundedCornerShape(16.dp))
                        .clickable(indication = null, interactionSource = remember { MutableInteractionSource() }){
                            onSelect()
                        },
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 4.dp, vertical = 12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            CustomRadioButton(
                                selected = selected,
                                onClick = onSelect,
                                selectedIcon = R.drawable.ic_selected_radio_btn,
                                unselectedIcon = R.drawable.ic_unselected_radio_btn
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                text = buildAnnotatedString {
                                    withStyle(
                                        style = SpanStyle(brush = textColorBrush)
                                    ) { append(title) }
                                },
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                textAlign = TextAlign.Justify,
                                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                modifier = Modifier.weight(0.3f)
                            )

                            if (tag != null) {
                                Text(
                                    text = tag,
                                    color = Color.Black,
                                    fontSize = 10.sp,
                                    textAlign = TextAlign.Center,
                                    fontFamily = FontFamily(Font(R.font.montserrat_bold)),
                                    modifier = Modifier
                                        .background(Color(0xFFFFE814), shape = RoundedCornerShape(16.dp))
                                        .padding(horizontal = 4.dp, vertical = 4.dp)
                                        .weight(0.20f)
                                )
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                text = price,
                                fontSize = 16.sp,
                                color = Color(0xFF364255),
                                fontFamily = FontFamily(Font(R.font.montserrat_bold)),
                                modifier = Modifier
                                    .padding(end = 8.dp)
                            )
                        }
                    }
                }

                // --- Discount Tag (only if available) ---
                if (offer != null) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize(Alignment.TopEnd)
                    ) {
                        DiscountTag(
                            text = offer,
                            modifier = Modifier
                                .offset(
                                    // offset proportionally to width but not too far
                                    x = (-finalTagWidth * 0.42f),
                                    y = finalTagHeight * 0.0f
                                )
                                .width(finalTagWidth)
                                .height(finalTagHeight)
                        )
                    }
                }
            }
        }

    }


}

@Composable
fun CustomRadioButton(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    selectedIcon: Int = R.drawable.ic_selected_radio_btn,
    unselectedIcon: Int = R.drawable.ic_unselected_radio_btn,
    contentDescription: String? = null
) {
    IconButton(
        onClick = onClick,
        modifier = modifier.size(24.dp)
    ) {
        Icon(
            painter = painterResource(id = if (selected) selectedIcon else unselectedIcon),
            contentDescription = contentDescription,
            tint = Color.Unspecified // keeps original icon color
        )
    }
}


@Composable
@Preview(showSystemUi = true)
fun ShowPremiumScreen() {
    UpgradeToPremiumScreen()
}