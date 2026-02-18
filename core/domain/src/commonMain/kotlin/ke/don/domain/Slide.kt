package ke.don.domain

import kotlinx.serialization.Serializable

@Serializable
sealed class Slide(
    val label: String,
    val showHeader: Boolean = true,
    val showFooter: Boolean = true,
    val isTitleScreen: Boolean = false,
    val transitionFromPrevious: ScreenTransition = ScreenTransition.Horizontal
){
    @Serializable
    data object Introduction: Slide(
        label = "Introduction",
        isTitleScreen = true,
        transitionFromPrevious = ScreenTransition.Fade
    )

    @Serializable
    data object UserMentalModel: Slide(
        label = "The User's Mental Model",
    )

    data object DeveloperProblem: Slide(
        label = "The Developer's Problem",
    )

    data object DeveloperObjective: Slide(
        label = "What we actually need",
    )

    data object KoinScopesRecap: Slide(
        label = "Koin Scopes Recap",
    )

    data object ModelingFlowAsScope: Slide(
        label = "Modeling a Flow as a Scope",
    )

    data object ModelingFlowAsScope2: Slide(
        label = "Modeling a Flow as a Scope: part 2",
    )
    data object WhatMakesAGoodScope: Slide(
        label = "What makes a good Scope",
    )



    data object ScopeLifeCycle: Slide(
        label = "Scope Lifecycle",
    )

    data object FlowBoundary: Slide(
        label = "Creating the Flow Boundary",
    )

    data object NavigationIntegration: Slide(
        label = "Using it in Compose",
    )

    data object PassingDownTheScope: Slide(
        label = "Passing Down the Scope",
    )

    data object ResultingBehaviours: Slide(
        label = "Resulting Behaviour",
    )

    data object WhenToUseScopes: Slide(
        label = "When To Use Scopes"
    )

    data object Conclusion: Slide(
        transitionFromPrevious = ScreenTransition.Vertical,
        label = "The End"
    )

    fun index(): Int {
        return getScreens().indexOf(this)
    }

    companion object {
        fun getScreens(): List<Slide> =
            listOf(
                Introduction,
                UserMentalModel,
                DeveloperProblem,
                DeveloperObjective,
                KoinScopesRecap,
                ModelingFlowAsScope,
                ModelingFlowAsScope2,
                WhatMakesAGoodScope,
                ScopeLifeCycle,
                //FlowBoundary,
                NavigationIntegration,
                PassingDownTheScope,
                //ResultingBehaviours,
                WhenToUseScopes,
                Conclusion
            )
    }
}

@Serializable
sealed interface ScreenTransition {
    object None : ScreenTransition
    object Fade : ScreenTransition
    object Horizontal : ScreenTransition
    object Vertical : ScreenTransition
}

@Serializable
enum class NavDirection {
    Forward, Backward
}