package ke.don.ski

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import ke.don.ski.navigation.rememberContainerState
import ke.don.ski.presentation.DeckMode

/**
 * Starts the Compose for Desktop application and opens the Slides and Presenter Notes windows.
 *
 * The Slides window uses a fullscreen state and presents the deck in presenter mode; the
 * Presenter Notes window displays the deck in local/notes mode and closing it does not exit
 * the application. Both windows share the same container state so their views stay synchronized.
 */
fun main() = application {
    // Slides / Audience window
    val containerState = rememberContainerState()
    val audienceWindowState = WindowState(
        placement = WindowPlacement.Fullscreen
    )
    val notesWindowState = WindowState(
        placement = WindowPlacement.Floating,
        size = DpSize(1300.dp, 800.dp)
    )

    // Remember to enable when presenting
    val doubleLaunch = false

    if (doubleLaunch) {
        Window(
            onCloseRequest = ::exitApplication,
            state = audienceWindowState,
            title = "Slides"
        ) {
            Deck(
                containerState = containerState,
                mode = DeckMode.Presenter
            )
        }
    }

    // Presenter / Notes window
    Window(
        onCloseRequest = {}, // closing notes shouldn't kill slides
        state = notesWindowState,
        title = "Presenter Notes"
    ) {
        Deck(
            containerState = containerState,
            mode = DeckMode.Local
        )
    }
}