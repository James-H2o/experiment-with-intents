package com.experiment.launch.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.experiment.launch.util.setupExitAnimation
import com.experiment.ui.theme.ExperimentWithIntentsTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private var isReady = false

    override fun onCreate(savedInstanceState: Bundle?) {

        val appSplashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        appSplashScreen.setKeepOnScreenCondition { !isReady }
        appSplashScreen.setupExitAnimation()

        lifecycleScope.launch {
            delay(2000L) // Fake load of app data
            isReady = true
        }

        enableEdgeToEdge()
        setContent {
            ExperimentWithIntentsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
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
