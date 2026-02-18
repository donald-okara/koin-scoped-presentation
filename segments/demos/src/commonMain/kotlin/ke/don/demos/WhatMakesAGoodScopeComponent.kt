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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import ke.don.demos.components.WordedList
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun WhatMakesAGoodScopeComponent(
    modifier: Modifier = Modifier,
) {
    var listIsVisible by remember { mutableStateOf(false) }

    val pointStyle = SpanStyle(
        fontWeight = FontWeight.Bold,
        brush = Brush.verticalGradient(
            colors = listOf(
                Color(0xFFFF6E40),
                MaterialTheme.colorScheme.primary
            )
        )
    )


    LaunchedEffect(Unit){
        delay(500)
        listIsVisible = true
    }

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
            "What makes a Good scope?",
            style = MaterialTheme.typography.headlineSmallEmphasized,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start
        )

        AnimatedVisibility(
            visible = listIsVisible
        ) {
            WordedList(
                points = listOf(
                    buildAnnotatedString {
                        withStyle(pointStyle) {
                            append("Stateful")
                        }
                        append(": They hold data that needs to persist across screens")
                    },

                    buildAnnotatedString {
                        withStyle(pointStyle) {
                            append("Flow-specific")
                        }
                        append(": Only relevant during checkout, not globally")
                    },

                    buildAnnotatedString {
                        withStyle(pointStyle) {
                            append("Interconnected")
                        }
                        append(": They may share data with each other")
                    },

                    buildAnnotatedString {
                        withStyle(pointStyle) {
                            append("Lifecycle-bound")
                        }
                        append(": Should live and die together")
                    },
                    )

            )
        }
    }
}