package com.happs.ximand.clothingtags.viewmodel.`object`

import com.happs.ximand.clothingtags.R
import com.happs.ximand.clothingtags.model.`object`.ClothingTag

class RemoveConfirmationDialog(val tag: ClothingTag) : ConfirmationDialog() {
    override var titleId: Int = R.string.removing_tag
    override var messageId: Int = R.string.removing_tag_confirmation
    override var positiveButtonId: Int = R.string.remove
}