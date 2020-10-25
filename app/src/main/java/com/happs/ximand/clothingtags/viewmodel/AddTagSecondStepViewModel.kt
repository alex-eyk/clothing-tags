package com.happs.ximand.clothingtags.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.happs.ximand.clothingtags.FragmentNavigation
import com.happs.ximand.clothingtags.R
import com.happs.ximand.clothingtags.view.AddTagThirdStepFragment
import com.happs.ximand.clothingtags.viewmodel.BaseAddTagViewModel

class AddTagSecondStepViewModel(savedState: SavedStateHandle) :
    BaseAddTagViewModel(savedState, 2) {

    override fun notifyOptionsMenuItemClicked(id: Int): Boolean {
        if (id == R.id.menu_next) {
            FragmentNavigation.getInstance()
                .navigateToNewFragment(AddTagThirdStepFragment.newInstance())
            return true
        }
        return super.notifyOptionsMenuItemClicked(id)
    }
}