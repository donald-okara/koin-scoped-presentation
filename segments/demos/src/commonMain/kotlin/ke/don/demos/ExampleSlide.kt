package ke.don.demos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ke.don.demos.components.AnimatedArrowFlowRow
import ke.don.demos.components.FlowItem

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ExampleSlide(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            "ExampleScreen",
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start
        )
    }
}

@Composable
fun AnimatedFlowExample(
    modifier: Modifier = Modifier
) {

    val items = listOf(
        FlowItem("Discover", Color(0xFF1E88E5)),
        FlowItem("Plan", Color(0xFFD81B60)),
        FlowItem("Build", Color(0xFF43A047)),
        FlowItem("Test", Color(0xFFF4511E)),
        FlowItem("Launch", Color(0xFF6D4C41))
    )

    Surface(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center
        ) {
            AnimatedArrowFlowRow(items = items)
        }
    }
}
