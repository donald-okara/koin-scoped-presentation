package ke.don.demos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.github.donald_okara.components.frames.defaultSkiFrames
import io.github.donald_okara.components.guides.code_viewer.FocusKotlinViewer
import io.github.donald_okara.components.guides.code_viewer.KotlinCodeViewerCard
import io.github.donald_okara.components.layout.HorizontallySegmentedScreen

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ScopeInScreen(
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
            "Using it in a Screen",
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
    1f to @Composable { BreakdownSegment() },
    1f to @Composable { CodeSegment() },
)

@Composable
private fun BreakdownSegment(
    modifier: Modifier = Modifier,
) {

    var isCardDark by remember {
        mutableStateOf(true)
    }

    var isFocusDark by remember {
        mutableStateOf(true)
    }
    var isCreateFocused by remember {
        mutableStateOf(false)
    }

    val fetchScopeCode = """
        val koin = getKoin()
        val scope = remember { koin.getScope(scopeID) } 
        val cartManager: CartManager = scope.get() 
        // This is how we resolve a class in scope 

    """.trimIndent()

    val closeScopeCode = """
BackHandler{ scope.close() }
// Mischief managed

    """.trimIndent()

    val frame = defaultSkiFrames().snake.create()
    frame.Render(
        modifier = modifier.fillMaxSize().padding(horizontal = 16.dp),
        header = null,
        footer = null,
    ) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()).fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(
                16.dp, Alignment.CenterVertically
            ),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Fetch the scope in the screen",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.ExtraBold
            )

            KotlinCodeViewerCard(
                modifier = modifier.fillMaxSize(),
                darkTheme = isCardDark,
                initiallyFolded = false,
                toggleFocus = { isCreateFocused = !isCreateFocused },
                toggleTheme = { isCardDark = !isCardDark }
            ) {
                fetchScopeCode
            }

            if (isCreateFocused) {
                FocusKotlinViewer(
                    onDismiss = { isCreateFocused = false },
                    darkTheme = isFocusDark,
                    title = "Fetch scope",
                    toggleTheme = { isFocusDark = !isFocusDark }
                ) {
                    fetchScopeCode
                }
            }

        }
    }
}

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
class CartScreen(
    private val scopeID: String
): Screen {
    @OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        val koin = getKoin()
        val scope = remember { koin.getScope(scopeID) } 
        val cartManager: CartManager = scope.get() 
        // This is how we resolve a class in scope 

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { ... }, 
            //Implement your own top bar
        ){ innerPadding ->
            CartScreenContent(
                modifier = Modifier.padding(innerPadding),
                onProceedToCheckout = { navigator?.push(PaymentScreen(scopeID)) },
                cartManager = cartManager
            )
        }
    }

}
    """.trimIndent()

    KotlinCodeViewerCard(
        modifier = modifier.fillMaxSize(),
        darkTheme = isCardDark,
        initiallyFolded = false,
        toggleFocus = { isFocused = !isFocused },
        toggleTheme = { isCardDark = !isCardDark }) {
        code
    }

    if (isFocused) {
        FocusKotlinViewer(
            onDismiss = { isFocused = false },
            darkTheme = isFocusDark,
            title = "Scope in Voyager Navigation",
            toggleTheme = { isFocusDark = !isFocusDark }) {
            code
        }
    }
}