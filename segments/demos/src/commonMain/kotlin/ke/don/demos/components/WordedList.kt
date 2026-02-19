package ke.don.demos.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp

@Composable
fun WordedList(
    modifier: Modifier = Modifier,
    points: List<AnnotatedString>
) {
    var visibleCount by remember { mutableStateOf(1) }

    LazyColumn(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        itemsIndexed(points) { index, item ->

            val isVisible = index < visibleCount

            AnimatedVisibility(
                visible = isVisible,
                enter = fadeIn() + slideInVertically { it / 2 },
                exit = fadeOut()
            ) {
                ListBullet(
                    text = item,
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .animateItem()
                        .clickable {
                            if (index == visibleCount - 1 && visibleCount < points.size) {
                                visibleCount++
                            }
                        }
                )
            }
        }
    }
}


@Composable
fun ListBullet(
    modifier: Modifier = Modifier,
    text: AnnotatedString
) {
    Box(
        modifier = modifier
            .padding(top = 8.dp),
    ){
        Row(
            modifier = Modifier
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFFFF6E40),
                                MaterialTheme.colorScheme.primary,
                            )
                        )
                    )
            )

            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}