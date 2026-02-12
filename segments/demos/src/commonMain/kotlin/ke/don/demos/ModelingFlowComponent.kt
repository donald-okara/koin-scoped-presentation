package ke.don.demos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
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
import io.github.donald_okara.components.guides.code_viewer.FocusKotlinViewer
import io.github.donald_okara.components.guides.code_viewer.KotlinCodeViewerCard

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ModellingFlowComponent(
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
        class CheckoutScope: KoinScopeComponent {
            override val scope: Scope by lazy { createScope(this) }
        
            fun close() = scope.close()
        }
    """.trimIndent()


    Column(
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth(0.6f),
        verticalArrangement = Arrangement.spacedBy(
            16.dp,
            Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.Start
    ) {

        Text(
            "Let's Define our scope component",
            style = MaterialTheme.typography.headlineSmallEmphasized,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start
        )


        KotlinCodeViewerCard(
            modifier = Modifier.fillMaxWidth(0.7f),
            darkTheme = isCardDark,
            toggleFocus = { isFocused = !isFocused },
            toggleTheme = { isCardDark = !isCardDark }
        ) {
            code
        }

        if (isFocused) {
            FocusKotlinViewer(
                onDismiss = { isFocused = false },
                darkTheme = isFocusDark,
                title = "Scope Component",
                toggleTheme = { isFocusDark = !isFocusDark }
            ) {
                code
            }
        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ModellingFlowComponent2(
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
        class CartManager {
            val items = mutableStateListOf<ShoppingItem>()  // reactive list
            fun addToCart(
                item: ShoppingItem,
            ){
                items.add(item)
                println("Added to cart")
            }
        
            fun removeFromCart(
                item: ShoppingItem,
            ){
                items.remove(item)
                println("Removed from cart")
            }
        }
        
        class PaymentManager(private val cartManager: CartManager){
            val items get() = cartManager.items
            fun checkout(){
                println("Checkout items: ${'$'}{cartManager.items}")
            }
        }
        
        class ReceiptManager(private val paymentManager: PaymentManager){
            val items get() = paymentManager.items
        }
    """.trimIndent()


    Column(
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth(0.6f),
        verticalArrangement = Arrangement.spacedBy(
            16.dp,
            Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.Start
    ) {

        Text(
            "Classes that live in the scope",
            style = MaterialTheme.typography.headlineSmallEmphasized,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start
        )


        KotlinCodeViewerCard(
            modifier = Modifier.fillMaxWidth(0.7f),
            darkTheme = isCardDark,
            initiallyFolded = true,
            toggleFocus = { isFocused = !isFocused },
            toggleTheme = { isCardDark = !isCardDark }
        ) {
            code
        }

        if (isFocused) {
            FocusKotlinViewer(
                onDismiss = { isFocused = false },
                darkTheme = isFocusDark,
                title = "Classes in the scope",
                toggleTheme = { isFocusDark = !isFocusDark }
            ) {
                code
            }
        }
    }
}