package com.example.demoscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.colorResource
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
            colorResource(R.color.gradient_start),
            colorResource(R.color.gradient_end)
        )
    )
// Border
    val borderBrush = if (selected) gradientBrush else SolidColor(Color.White)

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
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }) {
                            onSelect()
                        },
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
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
                                    textAlign = TextAlign.Center,
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold
                                    ),
                                    modifier = Modifier
                                        .background(
                                            colorResource(R.color.tag_background),
                                            shape = RoundedCornerShape(16.dp)
                                        )
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

@Preview(showSystemUi = true)
@Composable
fun PlanOptionPreview() {
    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        PlanOption(
            title = "Monthly",
            price = "$24.59",
            tag = "Popular",
            offer = "40% OFF",
            selected = true,
            onSelect = {})
    }

}