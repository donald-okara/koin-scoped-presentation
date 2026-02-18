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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.github.donald_okara.components.frames.defaultSkiFrames
import io.github.donald_okara.components.guides.code_viewer.FocusKotlinViewer
import io.github.donald_okara.components.guides.code_viewer.KotlinCodeViewerCard
import io.github.donald_okara.components.layout.HorizontallySegmentedScreen
import io.github.donald_okara.components.layout.VerticallySegmentedScreen
import ke.don.demos.components.AnimatedArrowFlowRow
import ke.don.demos.components.FlowItem

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ScopeLifeCycleComponent(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(
            16.dp,
            Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            "Adding the scope to Koin",
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
    1f to @Composable { CodeSegment() },
    1f to @Composable { ChartSegment() },
)

@Composable
private fun CodeSegment(
    modifier: Modifier = Modifier,
) {

    var isCardDark by remember {
        mutableStateOf(true)
    }

    var isFocusDark by remember {
        mutableStateOf(true)
    }
    var isFocused by remember {
        mutableStateOf(false)
    }

    val code = """
val scopeModule = module {
    scope<CheckoutScope> {
        scopedOf(::CartManager)
        scopedOf(::PaymentManager)
        scopedOf(::ReceiptManager)
    }
}
    """.trimIndent()

    KotlinCodeViewerCard(
        modifier = modifier.fillMaxSize(),
        darkTheme = isCardDark,
        initiallyFolded = false,
        toggleFocus = { isFocused = !isFocused },
        toggleTheme = { isCardDark = !isCardDark }
    ) {
        code
    }

    if (isFocused) {
        FocusKotlinViewer(
            onDismiss = { isFocused = false },
            darkTheme = isFocusDark,
            title = "Flow in Koin",
            toggleTheme = { isFocusDark = !isFocusDark }
        ) {
            code
        }
    }

}

@Composable
fun ChartSegment(
    modifier: Modifier = Modifier,
) {
    val frame = defaultSkiFrames().snake.create()
    val items = listOf(
        FlowItem("CartManager", Color(0xFF1E88E5)),
        FlowItem("PaymentManager", Color(0xFFD81B60)),
        FlowItem("ReceiptManager", Color(0xFF43A047)),
    )

    frame.Render(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        header = null,
        footer = null,
    ) {
        AnimatedArrowFlowRow(items = items)
    }
}