package com.happs.ximand.clothingtags.view.tagedit

import com.happs.ximand.clothingtags.R
import com.happs.ximand.clothingtags.databinding.FragmentAddSecondStepBinding
import com.happs.ximand.clothingtags.view.BaseEditTagFragment
import com.happs.ximand.clothingtags.viewmodel.EditTagSharedViewModel
import com.happs.ximand.clothingtags.viewmodel.tagedit.EditTagSecondStepViewModel

class EditTagSecondFragment :
    BaseEditTagFragment<EditTagSecondStepViewModel, EditTagSharedViewModel, FragmentAddSecondStepBinding>(
        R.layout.fragment_add_second_step, R.menu.menu_add_second_step
    ) {

    companion object {
        @JvmStatic
        fun newInstance(): EditTagSecondFragment {
            return EditTagSecondFragment()
        }
    }

}