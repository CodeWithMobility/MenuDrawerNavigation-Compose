package com.android4you.menunavigator.drawer.modifier


import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.android4you.menunavigator.drawer.state.MenuDrawerState
import com.android4you.menunavigator.drawer.state.isOpened
import kotlin.math.roundToInt

fun Modifier.shadow(
    color: Color,
    alpha: Float = 0.2f,
    borderRadius: Dp = 0.dp,
    shadowRadius: Dp = 20.dp,
    offsetY: Dp = 0.dp,
    offsetX: Dp = 0.dp
): Modifier = this.then(Modifier.drawBehind {
    val shadowColor = color.copy(alpha = alpha).toArgb()
    val transparent = color.copy(alpha = 0f).toArgb()

    this.drawIntoCanvas {
        val paint = Paint()
        val frameworkPaint = paint.asFrameworkPaint().apply {
            this.color = transparent
            this.setShadowLayer(
                shadowRadius.toPx(), offsetX.toPx(), offsetY.toPx(), shadowColor
            )
        }
        it.drawRoundRect(
            0f,
            0f,
            this.size.width,
            this.size.height,
            borderRadius.toPx(),
            borderRadius.toPx(),
            paint
        )
    }
})


@Composable
fun Modifier.conditionalModifier(
    condition: Int, drawerState: MenuDrawerState
): Modifier {
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current.density

    // Calculate screen width in pixels
    val screenWidth = (configuration.screenWidthDp * density).roundToInt()

    // Offset value based on the drawer state
    val offsetValue = (screenWidth / 4.5).dp

    // Shared animations based on drawer state
    val animatedOffset by animateDpAsState(
        targetValue = if (drawerState.isOpened()) offsetValue else 0.dp, label = "Animated Offset"
    )

    val animatedScale by animateFloatAsState(
        targetValue = if (drawerState.isOpened()) 0.8f else 1f, label = "Animated Scale"
    )
    val animatedRotation by animateFloatAsState(
        targetValue = if (drawerState.isOpened()) -12f else 0f, label = "Animated Rotation"
    )
    val animatedRotationZ by animateFloatAsState(
        targetValue = if (drawerState.isOpened()) -30f else 0f, // Flip 30 degrees
        label = "Animated Rotation Z"
    )

    val animatedRotationZNegative by animateFloatAsState(
        targetValue = if (drawerState.isOpened()) 30f else 0f, // Flip 30 degrees
        label = "Animated Rotation Z"
    )

    // Function to apply shared colored shadow modifier
    fun Modifier.applyShadow(): Modifier {
        return this.shadow(
            color = Color.White, alpha = 0.5f, shadowRadius = 50.dp
        )
    }

    // Apply the appropriate modifier based on the condition
    return when (condition) {
        1 -> this
            .offset { IntOffset(animatedOffset.roundToPx(), 0) }
            .scale(scale = animatedScale)
            .rotate(animatedRotation)
            .applyShadow()

        2 -> this
            .offset { IntOffset(animatedOffset.roundToPx(), animatedOffset.roundToPx()) }
            .applyShadow()

        3 -> this
            .offset { IntOffset(animatedOffset.roundToPx(), 0) }
            .scale(scale = animatedScale)
            .applyShadow()

        4 -> this
            .offset { IntOffset(animatedOffset.roundToPx(), 0) }
            .scale(scale = animatedScale)
            .graphicsLayer(
                rotationY = animatedRotationZ,  // 3D rotation along the Z-axis
                cameraDistance = 12f * density // Adjust for a more pronounced 3D effect
            )
            .applyShadow()
        5 -> this
            .offset { IntOffset(animatedOffset.roundToPx(), 0) }
            .scale(scale = animatedScale)
            .graphicsLayer(
                rotationY = animatedRotationZNegative,  // 3D rotation along the Z-axis
                cameraDistance = 12f * density // Adjust for a more pronounced 3D effect
            )
            .applyShadow()
        6 -> this
            .offset { IntOffset(animatedOffset.roundToPx(), 0) }
            .applyShadow()

        else -> this // Default modifier
    }
}
