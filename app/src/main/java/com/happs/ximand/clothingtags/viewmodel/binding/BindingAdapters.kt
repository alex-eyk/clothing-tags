package com.happs.ximand.clothingtags.viewmodel.binding

import android.graphics.Bitmap
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.google.android.material.chip.Chip
import com.happs.ximand.clothingtags.R
import com.happs.ximand.clothingtags.model.ImagesDaoImpl
import com.happs.ximand.clothingtags.viewmodel.util.AnimationHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class BindingAdapters {

    companion object {

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

        @JvmStatic
        @BindingAdapter("bitmap")
        fun setBitmap(imageView: ImageView, bitmap: Bitmap?) {
            if (bitmap == null) {
                imageView.setImageResource(R.drawable.image_placeholder)
            } else {
                val scope = CoroutineScope(Job() + Dispatchers.Main)
                scope.launch {
                    imageView.setImageBitmap(bitmap)
                }
            }
        }

        @JvmStatic
        @BindingAdapter("bitmapByImageId")
        fun setBitmapByImageId(imageView: ImageView, id: Int) {
            if (id == 0 || id == -1) {
                imageView.setImageResource(R.drawable.image_placeholder)
            } else {
                val scope = CoroutineScope(Job() + Dispatchers.Main)
                scope.launch {
                    val bmp = ImagesDaoImpl.instance?.loadImageById(id)
                    imageView.setImageBitmap(bmp)
                }
            }
        }
    }
}