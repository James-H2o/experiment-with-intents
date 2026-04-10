package com.experiment.feature.explicitintent.presentation

import android.content.Intent
import android.os.Build
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
import com.experiment.feature.explicitintent.FeatureActivityExtraModel
import com.experiment.ui.theme.ExperimentWithIntentsTheme

class FeatureActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()

        super.onCreate(savedInstanceState)

        // Retrieve data from Intent (using the modern API if on API 33+)
        val data = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(
                FEATURE_ACTIVITY_DATA_KEY,
                FeatureActivityExtraModel::class.java
            )
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(FEATURE_ACTIVITY_DATA_KEY)
        }

        setContent {
            ExperimentWithIntentsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FeatureEntryScreen(
                        data = data,
                        innerPadding = innerPadding,
                        onResult = { resultText ->
                            val resultIntent = Intent().apply {
                                putExtra(FEATURE_ACTIVITY_RETURN_KEY, resultText)
                            }
                            setResult(RESULT_OK, resultIntent)
                            finish() // This closes the activity and sends the result back
                        }
                    )
                }
            }
        }
    }

    companion object {
        const val FEATURE_ACTIVITY_DATA_KEY = "feature_activity_data_key"
        const val FEATURE_ACTIVITY_RETURN_KEY = "feature_activity_return_key"
    }
}

@Composable
fun FeatureEntryScreen(
    data: FeatureActivityExtraModel?,
    innerPadding: PaddingValues,
    onResult: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        contentAlignment = Alignment.Center
    ) {
        Column(Modifier.wrapContentSize()) {
            Text(data?.thing ?: "Text not received")
            Button(
                onClick = {
                    onResult("Back from the feature!")
                },
            ) {
                Text(
                    text = "Feature Button",
                )
            }
        }
    }
}
