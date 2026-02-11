package ke.don.demos

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ke.don.demos.components.WordedList
import ke.don.domain.Slide
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun KoinScopesRecapComponent(
    modifier: Modifier = Modifier
) {
    var listIsVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(500)
        listIsVisible = true
    }

    val pointStyle = SpanStyle(
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary
    )

    Column(
        modifier = modifier.fillMaxHeight().fillMaxWidth(0.6f),
        verticalArrangement = Arrangement.spacedBy(
            16.dp, Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            "Koin Scopes (Quick Recap)",
            style = MaterialTheme.typography.headlineSmallEmphasized,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start
        )

        Text(
            "What Koin gives us out of the box", style = MaterialTheme.typography.titleSmall
        )

        AnimatedVisibility(
            visible = listIsVisible
        ) {
            WordedList(
                points = listOf(
                    buildAnnotatedString {
                        withStyle(pointStyle) {
                            append("single")
                        }
                        append(": lives forever — your classic app-wide singleton.")
                    },

                    buildAnnotatedString {
                        withStyle(pointStyle) {
                            append("factory")
                        }
                        append(": is disposable — like a fresh cup of coffee each time.")
                    },

                    buildAnnotatedString {
                        withStyle(pointStyle) {
                            append("scoped")
                        }
                        append(": hangs around just long enough — perfect for a checkout session. (our main focus for today)")
                    })

            )
        }
    }
}