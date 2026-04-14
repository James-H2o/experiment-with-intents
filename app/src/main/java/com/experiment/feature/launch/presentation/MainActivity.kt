package com.experiment.feature.launch.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.experiment.feature.explicitintent.FeatureActivityExtraModel
import com.experiment.feature.explicitintent.presentation.FeatureActivity
import com.experiment.feature.explicitintent.presentation.FeatureActivity.Companion.FEATURE_ACTIVITY_DATA_KEY
import com.experiment.feature.explicitintent.presentation.FeatureActivity.Companion.FEATURE_ACTIVITY_RETURN_KEY
import com.experiment.feature.launch.util.setupExitAnimation
import com.experiment.ui.theme.ExperimentWithIntentsTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeScreenState(initialMessage: String) {
    var resultMessage by mutableStateOf(initialMessage)
}

class MainActivity : ComponentActivity() {
    // Using a simple state holder for practice instead of ViewModel
    private lateinit var homeState: HomeScreenState
    private var isReady = false

    // Registering the launcher
    private val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            homeState.resultMessage =
                result.data?.getStringExtra(FEATURE_ACTIVITY_RETURN_KEY)
                    ?: "No message was returned"
        }
    }

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
            // Experimenting with states and recomposition

            // Screen rotation causes Activity to be destroyed and recreated;
            // homeState and count by remember will be reset;
            // countSaved by rememberSaveable will persist.

            // println will cause recomposition of setContent;
            // only homeState is reset.
            var count by remember { mutableIntStateOf(0) }
            println("Outer scope recomposed. Current count: $count")

            var countSaved by rememberSaveable { mutableIntStateOf(0) }
            println("Outer scope recomposed. Saved count: $countSaved")

            homeState = HomeScreenState(initialMessage = "Waiting...")

            ExperimentWithIntentsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(
                        state = homeState,
                        count = count,
                        countSaved = countSaved,
                        incrementCountCallback = {
                            count++
                            countSaved++
                        },
                        innerPadding = innerPadding,
                        onLaunchIntent = { intent ->
                            startForResult.launch(intent)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun HomeScreen(
    state: HomeScreenState,
    count: Int,
    countSaved: Int,
    incrementCountCallback: () -> Unit,
    innerPadding: PaddingValues,
    onLaunchIntent: (Intent) -> Unit,
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        contentAlignment = Alignment.Center
    ) {
        Column(Modifier.wrapContentSize()) {
            Text("Count = $count")
            Text("Saved Count = $countSaved")
            Button(onClick = incrementCountCallback) {
                Text("Increment Counter")
            }
            Greeting(
                name = "Android",
            )
            Text(state.resultMessage)
            Button(
                onClick = {
                    val extra = FeatureActivityExtraModel(thing = "Greetings from MainActivity!")

                    val intent = Intent(context, FeatureActivity::class.java).apply {
                        putExtra(FEATURE_ACTIVITY_DATA_KEY, extra)
                    }

                    // Use if not expecting a result to be returned
//                    context.startActivity(intent)

                    // Callback to pass the intent
                    // to the ActivityResultLauncher<Intent>
                    // instance member of the host Activity
                    onLaunchIntent(intent)
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
