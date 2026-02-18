package ke.don.demos

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Code
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import io.github.donald_okara.components.layout.HorizontallySegmentedScreen
import io.github.donald_okara.components.values.Values
import ke.don.resources.Resources
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ConclusionSlide(
    modifier: Modifier = Modifier,
) {
    HorizontallySegmentedScreen(
        modifier = modifier,
        enableDrag = false,
        initialSegments = segments,
    )
}

private val segments = listOf(
    1.3f to @Composable { AnyQuestionsPicture() },
    0.7f to @Composable { SocialColumn() },
)

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun SocialColumn(
    modifier: Modifier = Modifier,
) {
    // Right: Contact block
    Column(
        modifier = modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(40.dp)
    ) {
        SocialBlock(
            label = "GitHub",
            url = "github.com/donald-okara"
        )

        SocialBlock(
            label = "Twitter",
            url = "x.com/don_okara"
        )

        SocialBlock(
            label = "LinkedIn",
            url = "linkedin.com/in/donald-isoe-a21310255"
        )

        SocialBlock(
            label = "Medium",
            url = "medium.com/@donaldokara123"
        )

        SocialBlock(
            label = "Further Reading",
            url = "medium.com/@donaldokara123/scoped-dependencies-in-koin-managing-flow-lifecycles-the-smart-way-99235728c7db"
        )
    }
}

@Composable
private fun SocialBlock(
    label: String,
    url: String,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        // Accent bar
        Box(
            modifier = Modifier
                .width(4.dp)
                .height(48.dp)
                .clip(RoundedCornerShape(2.dp))
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary,
                            Color(0xFFFF6E40)
                        )
                    )
                )
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = label.uppercase(),
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = url,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Medium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}



@Composable
fun AnyQuestionsPicture(
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(24.dp),
        tonalElevation = 8.dp,
        shadowElevation = 16.dp,
    ) {
        AnimatedGradientBackground{
            Image(
                painter = painterResource(Resources.Images.DAVID_S_PUMPKINS),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp)
            )
        }
    }
}

@Composable
fun AnimatedGradientBackground(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    // Infinite transition
    val transition = rememberInfiniteTransition()

    // Animate the gradient offset
    val offset by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3_000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFF6E40),
                        MaterialTheme.colorScheme.primary
                    ),
                    startY = 0f + offset * 1000f,   // animate startY
                    endY = 1000f + offset * 1000f  // animate endY
                )
            )
    ){
        content()
    }
}
