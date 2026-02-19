package ke.don.demos.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Pause
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.Stop
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.donald_okara.components.values.Values
import kotlin.math.PI
import kotlin.math.sin
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.delay

enum class TimerStatus { Idle, Resumed, Paused, Stopped }

@Composable
fun TimerComponent(
    modifier: Modifier = Modifier,
    totalTime: Duration = 10.seconds,
    interval: Duration = 1.seconds
) {
    var status by remember { mutableStateOf(TimerStatus.Idle) }
    var timeLeft by remember { mutableStateOf(totalTime) }

    // Fraction 0f..1f
    val progress = (timeLeft / totalTime).coerceIn(0.toDouble(), 1.toDouble())
    val animatedProgress by animateFloatAsState(
        targetValue = progress.toFloat(),
        animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing)
    )

    // Timer loop
    LaunchedEffect(status) {
        if (status == TimerStatus.Resumed) {
            while (timeLeft > Duration.ZERO) {
                delay(interval.inWholeMilliseconds)
                timeLeft -= interval
            }
            status = TimerStatus.Stopped
        }
    }

    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = modifier
            .animateContentSize()
            .widthIn(min = 160.dp)
            .height(64.dp)
            .clip(RoundedCornerShape(Values.cornerRadius))
            .background(Color.DarkGray)
            .clickable {
                status = when (status) {
                    TimerStatus.Idle, TimerStatus.Stopped, TimerStatus.Paused -> TimerStatus.Resumed
                    else -> TimerStatus.Paused
                }
                if (status == TimerStatus.Resumed && timeLeft <= Duration.ZERO) {
                    timeLeft = totalTime
                }
            }
    ) {
        // Wavy vertical fill
        WavyVerticalProgress(
            waveCount = 3,
            progress = animatedProgress,
            modifier = Modifier.matchParentSize(),
            colors = when {
                (status == TimerStatus.Stopped) || (status == TimerStatus.Idle) || (status == TimerStatus.Paused) -> listOf(
                    Color.Transparent,
                    Color.DarkGray
                )

                timeLeft <= Duration.ZERO -> listOf(Color.Red, Color.Red)
                progress <= 0.33f -> listOf(Color.Yellow, Color.Yellow)
                else -> listOf(Color(0xFFFF6E40), MaterialTheme.colorScheme.primary)
            }
        )

        // Timer text + dot
        TimerContent(
            status = status,
            timeLeft = timeLeft,
            modifier = Modifier.matchParentSize()
        )
    }
}

@Composable
fun TimerContent(
    status: TimerStatus,
    timeLeft: Duration,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = when (status) {
                TimerStatus.Idle -> Icons.Outlined.Timer
                TimerStatus.Resumed -> Icons.Outlined.Pause
                TimerStatus.Paused -> Icons.Outlined.PlayArrow
                TimerStatus.Stopped -> Icons.Outlined.Stop
            },
            contentDescription = null
        )

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = when (status) {
                TimerStatus.Idle -> "Start"
                TimerStatus.Resumed, TimerStatus.Paused -> formatTime(timeLeft.inWholeMilliseconds)
                TimerStatus.Stopped -> "Restart"
            },
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun WavyVerticalProgress(
    progress: Float,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = Values.cornerRadius,
    colors: List<Color> = listOf(Color(0xFFFF6E40), MaterialTheme.colorScheme.primary),
    waveAmplitude: Dp = 8.dp,
    waveFrequency: Float = 2f,
    waveSpeed: Int = 1000,
    waveCount: Int = 2 // number of overlapping waves
) {
    val infiniteTransition = rememberInfiniteTransition()
    val waveOffsets = List(waveCount) { index ->
        infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = waveSpeed + index * 300, // staggered speed
                    easing = LinearEasing
                ),
                repeatMode = RepeatMode.Restart
            )
        )
    }

    Canvas(modifier = modifier.clip(RoundedCornerShape(cornerRadius))) {
        val widthPx = size.width
        val heightPx = size.height
        val fillHeight = heightPx * progress
        val amplitudePx = waveAmplitude.toPx()

        repeat(waveCount) { i ->
            val path = Path().apply {
                moveTo(0f, heightPx)
                for (x in 0..widthPx.toInt()) {
                    val phase = waveOffsets[i].value * 2 * PI
                    val y = heightPx - fillHeight +
                            amplitudePx * sin((x / widthPx) * waveFrequency * 2 * PI + phase).toFloat()
                    lineTo(x.toFloat(), y)
                }
                lineTo(widthPx, heightPx)
                close()
            }

            // Slightly reduce alpha for overlapping effect
            drawPath(
                path = path,
                brush = Brush.verticalGradient(
                    colors = colors.map { it.copy(alpha = 0.5f) }, // half transparency
                    startY = heightPx - fillHeight - amplitudePx,
                    endY = heightPx
                )
            )
        }
    }
}


// Helper functions
private fun formatTime(millis: Long): String {
    val totalSeconds = millis / 1000
    val hours = totalSeconds / 3600
    val minutes = (totalSeconds % 3600) / 60
    val seconds = totalSeconds % 60
    return when {
        hours > 0 -> "${hours}h ${minutes.pad2()}m"
        minutes > 0 -> "${minutes}m ${seconds.pad2()}s"
        else -> "${seconds}s"
    }
}

private fun Long.pad2(): String = if (this < 10) "0$this" else this.toString()