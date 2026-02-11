package ke.don.ski.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import io.github.donald_okara.components.guides.notes.Notes
import ke.don.demos.AnimatedFlowExample
import ke.don.demos.DeveloperProblemComponent
import ke.don.demos.ExampleSlide
import ke.don.demos.HorizontalSegmentsDemo
import ke.don.demos.KodeViewerSlide
import ke.don.demos.KoinScopesRecapComponent
import ke.don.demos.UserMentalModelSlide
import ke.don.demos.VerticalSegmentsDemo
import ke.don.demos.WhatWeNeedComponent
import ke.don.domain.Slide
import ke.don.introduction.IntroductionScreen

/**
 * Displays the UI screen corresponding to the provided slide.
 *
 * @param modifier Modifier to be applied to the displayed screen.
 * @param slide The Slide value that determines which composable screen to show.
 */
@Composable
fun SlideSwitcher(
    modifier: Modifier = Modifier,
    slide: Slide,
) {
    when (slide) {
        Slide.Introduction -> IntroductionScreen(modifier)
        Slide.UserMentalModel -> UserMentalModelSlide(modifier)
        Slide.DeveloperProblem -> DeveloperProblemComponent(modifier)
        Slide.DeveloperObjective -> WhatWeNeedComponent(modifier)
        Slide.KoinScopesRecap -> KoinScopesRecapComponent(modifier)

        else -> {}
    }
}

/**
 * Provide speaker notes for the specified slide.
 *
 * @param slide The slide to produce notes for.
 * @return `Notes` for the specified slide, or `null` if no notes are available.
 */
@Composable
fun slidesNotes(
    slide: Slide,
): Notes? = when (slide) {
    Slide.Introduction -> Notes(
        title = "Introduction",
        points = listOf(
            AnnotatedString("Remember to Mention your title"),
            AnnotatedString("Keep the back story short"),
            AnnotatedString(""),
            AnnotatedString(""),
            AnnotatedString("This talk is about flows, not screens"),
            AnnotatedString("The problem isn’t “how do I inject?” but “how long should this thing live?")
        )
    )
    Slide.UserMentalModel -> Notes(
        title = "User Mental Model",
        points = listOf(
            AnnotatedString("Users never think about state lifetimes"),
            AnnotatedString("They expect continuity without leakage"),
            AnnotatedString("Losing cart data = broken trust")
        )
    )
    Slide.DeveloperProblem -> Notes(
        title = "Developer Problem",
        points = listOf(
            AnnotatedString("This is where “just put it in the ViewModel” fails"),
            AnnotatedString("Especially obvious in multi-step flows"),
        )
    )
    else -> null
}