package com.happs.ximand.clothingtags.viewmodel.binding

import android.net.Uri
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.google.android.material.chip.Chip
import com.happs.ximand.clothingtags.viewmodel.util.AnimationHelper

class BindingAdapters {

    companion object {
        @JvmStatic
        @BindingAdapter("imageFromUri")
        fun loadImageFromUri(imageView: ImageView, uri: Uri) {

        }

        @JvmStatic
        @BindingAdapter("chipContent")
        fun setChipContent(chip: Chip, resId: Int) {
            if (resId == 0) {
                chip.visibility = View.GONE
            } else {
                chip.visibility = View.VISIBLE
                chip.setText(resId)
            }
        }

        @JvmStatic
        @BindingAdapter("setClipToOutline")
        fun setClipToOutline(view: View, set: Boolean) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                view.clipToOutline = set
            }
        }

        @JvmStatic
        @BindingAdapter("visibility")
        fun setVisibility(view: View, visible: Boolean) {
            if (visible) {
                view.visibility = View.VISIBLE
                AnimationHelper.appearanceAnimation(view)
            } else {
                if (view.visibility == View.GONE) {
                    return
                } else {
                    AnimationHelper.disappearanceAnimation(view)
                    Handler(Looper.getMainLooper()).postDelayed(
                        { view.visibility = View.GONE },
                        AnimationHelper.DURATION
                    )
                }
            }
        }
    }
}