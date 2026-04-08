package com.experiment.launch.util

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen

fun SplashScreen.setupExitAnimation() {
    this.setOnExitAnimationListener { splashScreenView ->
        val scaleX = ObjectAnimator.ofFloat(splashScreenView.iconView, View.SCALE_X, 1f, 0f)
        val scaleY = ObjectAnimator.ofFloat(splashScreenView.iconView, View.SCALE_Y, 1f, 0f)
        val alpha = ObjectAnimator.ofFloat(splashScreenView.view, View.ALPHA, 1f, 0f)

        AnimatorSet().apply {
            setDuration(500L)
            playTogether(scaleX, scaleY, alpha)

            interpolator = AnticipateInterpolator()

            doOnEnd {
                splashScreenView.remove()
            }
            start()
        }
    }
}
