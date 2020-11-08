package com.happs.ximand.clothingtags.viewmodel.tagadd

import androidx.lifecycle.SavedStateHandle
import com.happs.ximand.clothingtags.FragmentNavigation
import com.happs.ximand.clothingtags.view.tagadd.AddTagThirdStepFragment
import com.happs.ximand.clothingtags.viewmodel.BaseEditTagViewModel

class AddTagSecondStepViewModel(savedState: SavedStateHandle) :
    BaseEditTagViewModel(savedState, 2) {

    override fun navigateToNextFragment() {
        FragmentNavigation.getInstance()
            .navigateToNewFragment(AddTagThirdStepFragment.newInstance())
    }

}