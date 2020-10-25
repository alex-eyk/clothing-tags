package com.happs.ximand.clothingtags.viewmodel

import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import com.happs.ximand.clothingtags.FragmentNavigation
import com.happs.ximand.clothingtags.R
import com.happs.ximand.clothingtags.SingleLiveEvent
import com.happs.ximand.clothingtags.view.AddTagSecondStepFragment

class AddTagFirstStepViewModel(savedState: SavedStateHandle) : BaseAddTagViewModel(savedState, 1) {

    var image: Bitmap? = null
    val selectImageEvent = SingleLiveEvent<Void>()
    val cropImageEvent = SingleLiveEvent<Void>()

    override fun notifyOptionsMenuItemClicked(id: Int): Boolean {
        if (id == R.id.menu_next) {
            FragmentNavigation.getInstance()
                .navigateToNewFragment(AddTagSecondStepFragment.newInstance())
            return true
        }
        return super.notifyOptionsMenuItemClicked(id)
    }

    fun selectImage() {
        selectImageEvent.call()
    }

    fun notifyImageSelected(uri: Uri) {

    }

    fun cropImage() {
        cropImageEvent.call()
    }
}