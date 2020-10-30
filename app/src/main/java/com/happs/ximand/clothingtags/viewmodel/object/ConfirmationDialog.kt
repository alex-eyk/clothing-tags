package com.happs.ximand.clothingtags.viewmodel.`object`

import com.happs.ximand.clothingtags.R

abstract class ConfirmationDialog {
    abstract var titleId: Int
    abstract var messageId: Int
    abstract var positiveButtonId: Int
    val negativeButtonId: Int = R.string.default_negative_button
}