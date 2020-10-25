package com.happs.ximand.clothingtags.viewmodel.util

import android.animation.ObjectAnimator
import android.view.View

object AnimationHelper {

    const val DURATION = 300L;

    fun appearanceAnimation(view: View) {
        alphaAnimation(view, 0f, 1f)
    }

    fun disappearanceAnimation(view: View) {
        alphaAnimation(view, 1f, 0f)
    }

    private fun alphaAnimation(view: View, vararg values: Float) {
        ObjectAnimator
            .ofFloat(view, View.ALPHA, *values)
            .setDuration(DURATION)
            .start()
    }

}