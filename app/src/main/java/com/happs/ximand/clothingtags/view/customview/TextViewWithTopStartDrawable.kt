package com.happs.ximand.clothingtags.view.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.text.TextUtils
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import kotlin.math.roundToInt

class TextViewWithTopStartDrawable : AppCompatTextView {

    private val bounds = Rect()

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr)

    override fun onDraw(canvas: Canvas) {
        if (!TextUtils.isEmpty(text)) {
            val drawables = compoundDrawables
            val drawableLeft = drawables[0]
            if (drawableLeft != null) {
                val fontMetricsInt = paint.fontMetricsInt
                paint.getTextBounds(text as String, 0, length(), bounds)
                val textVerticalSpace =
                    (bounds.top - fontMetricsInt.top.toFloat()).roundToInt()
                val offset =
                    (height - drawableLeft.intrinsicHeight) / 2 - textVerticalSpace - paddingTop / 2
                drawableLeft.setBounds(
                    0,
                    -offset,
                    drawableLeft.intrinsicWidth,
                    drawableLeft.intrinsicHeight - offset
                )
            }
        }
        super.onDraw(canvas)
    }
}