package com.experiment.feature.launch.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.experiment.feature.explicitintent.FeatureActivityExtraModel
import com.experiment.feature.explicitintent.presentation.FeatureActivity
import com.experiment.feature.explicitintent.presentation.FeatureActivity.Companion.FEATURE_ACTIVITY_DATA
import com.experiment.feature.launch.util.setupExitAnimation
import com.experiment.ui.theme.ExperimentWithIntentsTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private var isReady = false

    override fun onCreate(savedInstanceState: Bundle?) {
        val appSplashScreen = installSplashScreen()
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        appSplashScreen.setKeepOnScreenCondition { !isReady }
        appSplashScreen.setupExitAnimation()

        lifecycleScope.launch {
            delay(2000L) // Fake load of app data
            isReady = true
        }

        setContent {
            ExperimentWithIntentsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(innerPadding)
                }
            }
        }
    }
}

@Composable
fun HomeScreen(innerPadding: PaddingValues) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        contentAlignment = Alignment.Center
    ) {
        Column(Modifier.wrapContentSize()) {
            Greeting(
                name = "Android",
                modifier = Modifier.padding(innerPadding)
            )
            Button(
                onClick = {
                    val extra = FeatureActivityExtraModel(thing = "Greetings from MainActivity!")

                    val intent = Intent(context, FeatureActivity::class.java).apply {
                        putExtra(FEATURE_ACTIVITY_DATA, extra)
                    }

                    context.startActivity(intent)
                },
            ) {
                Text(
                    text = "Explicit Intent",
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ExperimentWithIntentsTheme {
        Greeting("Android")
    }
}
