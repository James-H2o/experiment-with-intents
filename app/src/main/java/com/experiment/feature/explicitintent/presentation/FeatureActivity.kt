package com.experiment.feature.explicitintent.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.experiment.ui.theme.ExperimentWithIntentsTheme

class FeatureActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Retrieve the data (using the modern API if on API 33+)
        val data = if (android.os.Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(FEATURE_ACTIVITY_DATA, ExperimentData::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(FEATURE_ACTIVITY_DATA)
        }

        setContent {
            ExperimentWithIntentsTheme {
                // This represents the "Entry Screen" of this separate feature
                FeatureEntryScreen(data)
            }
        }
    }

    companion object {
        const val FEATURE_ACTIVITY_DATA = "feature_activity_data_key"
    }
}

// Placeholder
data class ExperimentData(val thing: String)

@Composable
fun FeatureEntryScreen(data: ExperimentData?) {

}
