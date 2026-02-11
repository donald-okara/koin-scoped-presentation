package ke.don.demos.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

data class FlowItem(
    val text: String,
    val color: Color
)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AnimatedArrowFlowRow(
    modifier: Modifier = Modifier,
    items: List<FlowItem>,
) {
    var visibleCount by remember { mutableStateOf(1) }

    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items.forEachIndexed { index, item ->

            val isVisible = index < visibleCount

            AnimatedVisibility(
                visible = isVisible,
                enter = fadeIn(animationSpec = tween(300)) +
                        scaleIn(
                            animationSpec = tween(300),
                            initialScale = 0.8f
                        )
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {

                    OutlinedSurfaceItem(
                        item = item,
                        onClick = {
                            if (index == visibleCount - 1 &&
                                visibleCount < items.size
                            ) {
                                visibleCount++
                            }
                        }
                    )

                    if (index < items.lastIndex) {
                        Spacer(Modifier.width(4.dp))
                        Arrow()
                    }
                }
            }
        }
    }
}


@Composable
private fun OutlinedSurfaceItem(
    modifier: Modifier = Modifier,
    item: FlowItem,
    onClick: () -> Unit
) {
    Surface(
        border = BorderStroke(
            width = 1.dp,
            color = item.color,
        ),
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = item.text,
            color = item.color,
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}

@Composable
private fun Arrow() {
    Text("→")
}

