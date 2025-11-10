package com.example.demoscreen.ui.theme

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.demoscreen.R

@Composable
fun FeatureCard(
    modifier: Modifier = Modifier,
    isLandscape: Boolean = false,
    cardOverlapOffset: Dp = 0.dp
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .offset(y = -cardOverlapOffset)
            .zIndex(1f)
            .padding(horizontal = 0.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(if (isLandscape) 0.9f else 0.9f)
                .shadow(elevation = 8.dp, shape = RoundedCornerShape(16.dp))
                .clip(RoundedCornerShape(16.dp)),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(Modifier.padding(16.dp)) {
                val features = listOf(
                    "AI on Your Keyboard" to R.drawable.ic_feature_1,
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
                            modifier = Modifier.size(24.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = iconRes),
                                contentDescription = feature,
                                tint = Color.Unspecified,
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
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun FeatureCardPreview(){
    FeatureCard()
}
