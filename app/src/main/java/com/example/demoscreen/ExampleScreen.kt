package com.example.demoscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.demoscreen.ui.theme.FeatureCard

@Composable
fun ExampleScreen() {
    Column {
        FeatureCard(
            isLandscape = false,
            cardOverlapOffset = 20.dp
        )

        Spacer(Modifier.height(16.dp))

        FeatureCard(
            isLandscape = true,
            cardOverlapOffset = 0.dp
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun ExampleScreenPreview(){
    ExampleScreen()
}
