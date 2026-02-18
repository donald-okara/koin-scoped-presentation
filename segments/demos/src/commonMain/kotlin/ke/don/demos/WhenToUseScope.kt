package ke.don.demos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.github.donald_okara.components.frames.defaultSkiFrames
import io.github.donald_okara.components.layout.HorizontallySegmentedScreen
import ke.don.demos.components.WordedList

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun WhenToUseScopesComponent(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(8.dp).fillMaxWidth().fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(
            16.dp, Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            "When should we use scopes",
            style = MaterialTheme.typography.headlineSmallEmphasized,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start
        )

        HorizontallySegmentedScreen(
            modifier = modifier,
            enableDrag = false,
            initialSegments = segments,
        )
    }
}

private val segments = listOf(
    1f to @Composable { Dos() },
    1f to @Composable { Donts() },
)

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun Donts(
    modifier: Modifier = Modifier,
) {
    val frame = defaultSkiFrames().snake.create()
    frame.Render(
        modifier = modifier.fillMaxSize().padding(horizontal = 16.dp),
        header = null,
        footer = null,
    ) {
        Column(
            modifier = modifier
                .fillMaxHeight()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                "When should we NOT use scopes",
                style = MaterialTheme.typography.titleSmallEmphasized,
                color = MaterialTheme.colorScheme.error,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start
            )

            WordedList(
                modifier = modifier,
                points = listOf(
                    AnnotatedString("For app-wide dependencies that should live as singletons (e.g. analytics, repositories, preferences)"),
                    AnnotatedString("For stateless or lightweight objects that can be created with factory"),
                    AnnotatedString("As a replacement for proper ViewModel usage tied to screen lifecycle"),
                    AnnotatedString("When you are unsure who is responsible for closing the scope")
                )
            )
        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun Dos(
    modifier: Modifier = Modifier,
) {
    val frame = defaultSkiFrames().snake.create()
    frame.Render(
        modifier = modifier.fillMaxSize().padding(horizontal = 16.dp),
        header = null,
        footer = null,
    ) {
        Column(
            modifier = modifier
                .fillMaxHeight()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                "When should we use scopes",
                style = MaterialTheme.typography.titleSmallEmphasized,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start
            )

            WordedList(
                modifier = modifier,
                points = listOf(
                    AnnotatedString("When managing a multi-screen flow that shares temporary state (e.g. checkout, onboarding, multi-step forms)"),
                    AnnotatedString("When state must survive navigation changes but should be cleared once the flow ends"),
                    AnnotatedString("When several related classes depend on each other within a single user journey"),
                    AnnotatedString("When you want lifecycle control without bloating a shared ViewModel")
                )
            )
        }
    }
}
