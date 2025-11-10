package com.example.demoscreen

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import kotlin.math.min

class BottomArcShape(
    private val arcHeightDp: Float = 80f
) : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val arcHeightPx = with(density) { arcHeightDp.dp.toPx() }
        val width = size.width
        val height = size.height
        val arcDepth = min(arcHeightPx, height / 2)

        val path = Path().apply {
            moveTo(0f, 0f)
            lineTo(0f, height - arcDepth)

            // Draw a half-circle (bottom curve)
            this.quadraticTo(
                width / 2f, height + arcDepth, // control point (bottom center)
                width, height - arcDepth        // end point
            )

            lineTo(width, 0f)
            close()
        }

        return Outline.Generic(path)
    }
}