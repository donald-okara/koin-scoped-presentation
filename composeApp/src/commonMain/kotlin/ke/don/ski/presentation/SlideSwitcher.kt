package ke.don.ski.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import io.github.donald_okara.components.guides.notes.Notes
import ke.don.demos.AnimatedFlowExample
import ke.don.demos.DeveloperProblemComponent
import ke.don.demos.ExampleSlide
import ke.don.demos.HorizontalSegmentsDemo
import ke.don.demos.KodeViewerSlide
import ke.don.demos.KoinScopesRecapComponent
import ke.don.demos.ModellingFlowComponent
import ke.don.demos.ModellingFlowComponent2
import ke.don.demos.ScopeInNavigation
import ke.don.demos.ScopeInScreen
import ke.don.demos.ScopeLifeCycleComponent
import ke.don.demos.UserMentalModelSlide
import ke.don.demos.VerticalSegmentsDemo
import ke.don.demos.WhatMakesAGoodScopeComponent
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
        Slide.ModelingFlowAsScope -> ModellingFlowComponent(modifier)
        Slide.ModelingFlowAsScope2 -> ModellingFlowComponent2(modifier)
        Slide.ScopeLifeCycle -> ScopeLifeCycleComponent(modifier)
        Slide.WhatMakesAGoodScope -> WhatMakesAGoodScopeComponent(modifier)
        Slide.NavigationIntegration -> ScopeInNavigation(modifier)
        Slide.PassingDownTheScope -> ScopeInScreen(modifier)
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
        title = "Introduction", points = listOf(
            AnnotatedString("Remember to Mention your title"),
            AnnotatedString("Keep the back story short"),
            AnnotatedString(""),
            AnnotatedString(""),
            AnnotatedString("This talk is about flows, not screens"),
            AnnotatedString("The problem isn’t “how do I inject?” but “how long should this thing live?")
        )
    )

    Slide.UserMentalModel -> Notes(
        title = "User Mental Model", points = listOf(
            AnnotatedString("Users never think about state lifetimes"),
            AnnotatedString("They expect continuity without leakage"),
            AnnotatedString("Losing cart data = broken trust")
        )
    )

    Slide.DeveloperProblem -> Notes(
        title = "Developer Problem", points = listOf(
            AnnotatedString("This is where “just put it in the ViewModel” fails"),
            AnnotatedString("Especially obvious in multi-step flows"),
        )
    )

    Slide.ModelingFlowAsScope -> Notes(
        title = "Modeling a Flow as a Scope", points = listOf(
            AnnotatedString("Here CheckoutScope acts also as a boundary and a type safe unique identifier for our scope"),
            AnnotatedString("Provides a close method to close the scope"),
            AnnotatedString("CheckoutScope represents the lifecycle of our flow"),
            AnnotatedString("The alternative (qualifiers) can lead to leaks or even crashes if call site has a typo"),
        )
    )

    Slide.ModelingFlowAsScope2 -> Notes(
        title = "Modeling a Flow as a Scope: part 2",
        points = listOf(buildAnnotatedString {
            append("The classes here represent ")
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append("distinct steps in the checkout flow")
            }
            append(", each with a ")
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append("single responsibility")
            }
        }, buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append("CartManager")
            }
            append(" handles cart state, ")
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append("PaymentManager")
            }
            append(" handles checkout logic, ")
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append("ReceiptManager")
            }
            append(" displays receipt data")
        }, buildAnnotatedString {
            append("Dependencies mirror user progression: ")
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append("CartManager → PaymentManager → ReceiptManager")
            }
        }, buildAnnotatedString {
            append("Shared ")
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append("mutableStateListOf")
            }
            append(" in CartManager acts as ")
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append("single source of truth")
            }
            append(" - no prop drilling or state duplication")
        }, buildAnnotatedString {
            append("All classes are ")
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append("scoped together")
            }
            append(" - they ")
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append("live and die as one unit")
            }
            append(" when the scope closes")
        }, buildAnnotatedString {
            append("Good scope candidates: ")
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append("stateful, flow-specific, interconnected, and lifecycle-bound")
            }
        }),

        )

    Slide.ScopeLifeCycle -> Notes(
        title = "Scope Lifecycle", points = listOf(
            AnnotatedString(
                "Here, we tell Koin how to create each class when it's active — and how to destroy them once it’s closed."
            )
        )
    )

    else -> null
}