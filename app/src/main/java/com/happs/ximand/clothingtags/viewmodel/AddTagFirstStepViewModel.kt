package com.happs.ximand.clothingtags.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.happs.ximand.clothingtags.FragmentNavigation
import com.happs.ximand.clothingtags.R
import com.happs.ximand.clothingtags.view.AddTagSecondStepFragment

class AddTagFirstStepViewModel(savedState: SavedStateHandle) : BaseAddTagViewModel(savedState, 1) {

    override fun notifyOptionsMenuItemClicked(id: Int): Boolean {
        if (id == R.id.menu_next) {
            FragmentNavigation.getInstance()
                .navigateToNewFragment(AddTagSecondStepFragment.newInstance())
            return true
        }
        return super.notifyOptionsMenuItemClicked(id)
    }

}