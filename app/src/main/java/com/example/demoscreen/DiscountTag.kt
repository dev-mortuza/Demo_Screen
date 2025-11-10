package com.example.demoscreen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.demoscreen.ui.theme.MontserratFontFamily

@Composable
fun DiscountTag(
    text: String = "",
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.clip(RoundedCornerShape(3.dp)),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val w = size.width
            val h = size.height*.70f

            val bottomW = w * 0.6f
            val sideInset = (w - bottomW) / 1.5f
            val topRadius = h * 0.080f

            val path = Path().apply {
                moveTo(topRadius, 0f)
                lineTo(w - topRadius, 0f)
                quadraticTo(w, 0f, w, topRadius)
                quadraticTo(w - sideInset * 0.6f, h * 0.65f, w - sideInset, h)
                lineTo(sideInset, h)
                quadraticTo(sideInset * 0.6f, h * 0.65f, 0f, topRadius)
                quadraticTo(0f, 0f, topRadius, 0f)
                close()
            }

            drawPath(
                path = path,
                brush = Brush.horizontalGradient(
                    listOf(Color(0xFF695AF7), Color(0xFFCF56C9))
                )
            )

            val r = 2.dp.toPx()
            val gap = r * 1.2f

            drawCircle(
                color = Color(0xFF695AF7),
                radius = r,
                center = Offset(sideInset, h + gap)
            )
            drawCircle(
                color = Color(0xFFCF56C9),
                radius = r,
                center = Offset(w - sideInset, h + gap)
            )
        }

        // ✅ Move text slightly upward (~12% of height)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 0.dp) // reset
                .offset(y = (-1).dp)    // base adjustment
                .graphicsLayer {
                    translationY = -4f // fine pixel correction for crisp rendering
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.Normal,
                fontSize = 8.sp,
                fontFamily = MontserratFontFamily,
                textAlign = TextAlign.Center,
                lineHeight = 8.sp
            )
        }
    }
}




@Preview(showSystemUi = true)
@Composable
fun DiscountTagPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F4F4)),
        contentAlignment = Alignment.Center
    ) {
        DiscountTag(
            modifier = Modifier
                .width(240.dp)
                .height(70.dp),
            text = "50% OFF"
        )
    }
}
