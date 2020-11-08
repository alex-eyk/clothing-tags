package com.happs.ximand.clothingtags.viewmodel.tagedit

import androidx.lifecycle.SavedStateHandle
import com.happs.ximand.clothingtags.FragmentNavigation
import com.happs.ximand.clothingtags.view.tagedit.EditTagSecondFragment
import com.happs.ximand.clothingtags.viewmodel.BaseEditTagViewModel

class EditTagFirstStepViewModel(savedState: SavedStateHandle) :
    BaseEditTagViewModel(savedState, 1) {

    override fun navigateToNextFragment() {
        FragmentNavigation.getInstance()
            .navigateToNewFragment(EditTagSecondFragment.newInstance())
    }

}