package com.happs.ximand.clothingtags.viewmodel.tagadd

import androidx.lifecycle.SavedStateHandle
import com.happs.ximand.clothingtags.FragmentNavigation
import com.happs.ximand.clothingtags.view.tagadd.AddTagSecondStepFragment
import com.happs.ximand.clothingtags.viewmodel.BaseEditTagViewModel

class AddTagFirstStepViewModel(savedState: SavedStateHandle) :
    BaseEditTagViewModel(savedState, 1) {

    override fun navigateToNextFragment() {
        FragmentNavigation.getInstance()
            .navigateToNewFragment(AddTagSecondStepFragment.newInstance())
    }

}