package com.happs.ximand.clothingtags.viewmodel.tagedit

import androidx.lifecycle.SavedStateHandle
import com.happs.ximand.clothingtags.FragmentNavigation
import com.happs.ximand.clothingtags.view.tagedit.EditTagThirdFragment
import com.happs.ximand.clothingtags.viewmodel.BaseEditTagViewModel

class EditTagSecondStepViewModel(savedState: SavedStateHandle) :
    BaseEditTagViewModel(savedState, 2) {

    override fun navigateToNextFragment() {
        FragmentNavigation.getInstance()
            .navigateToNewFragment(EditTagThirdFragment.newInstance())
    }

}