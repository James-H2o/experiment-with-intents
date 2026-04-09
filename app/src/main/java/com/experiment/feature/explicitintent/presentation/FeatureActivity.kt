package com.experiment.feature.explicitintent.presentation

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

        // Retrieve the data (using the modern API if on API 33+)
        val data = if (android.os.Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(FEATURE_ACTIVITY_DATA, FeatureActivityExtraModel::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(FEATURE_ACTIVITY_DATA)
        }

        setContent {
            ExperimentWithIntentsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FeatureEntryScreen(data, innerPadding)
                }
            }
        }
    }

    companion object {
        const val FEATURE_ACTIVITY_DATA = "feature_activity_data_key"
    }
}

@Composable
fun FeatureEntryScreen(data: FeatureActivityExtraModel?, innerPadding: PaddingValues) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        contentAlignment = Alignment.Center
    ) {
        Column(Modifier.wrapContentSize()) {
            Text(data?.thing ?: "Text not received")
            Button(
                onClick = {},
            ) {
                Text(
                    text = "Feature Button",
                )
            }
        }
    }
}
