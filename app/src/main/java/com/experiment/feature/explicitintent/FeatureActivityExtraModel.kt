package com.experiment.feature.explicitintent

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FeatureActivityExtraModel(
    val thing: String
) : Parcelable
