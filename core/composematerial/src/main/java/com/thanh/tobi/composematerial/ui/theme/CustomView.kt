package com.thanh.tobi.composematerial.ui.theme

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SquareComponent(contentSize: Dp = 300.dp) {
    val canvasSize = with(LocalDensity.current) {
        contentSize.toPx()
    }
    val infiniteScale = rememberInfiniteTransition()
    val animatedDotScale = infiniteScale.animateFloat(
        initialValue = 20f,
        targetValue = canvasSize / 2,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutLinearInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    Canvas(
        modifier = Modifier
            .size(contentSize)
    ) {
        drawRect(
            brush = Brush.linearGradient(
                colors = listOf(Color.LightGray, Color.Gray)
            ),
            size = size
        )

        drawCircle(
            color = Color.White,
            center = Offset(x = size.width / 2f, y = size.height / 2f),
            radius = animatedDotScale.value
        )
    }
}