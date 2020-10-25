package com.happs.ximand.clothingtags.view

import com.happs.ximand.clothingtags.R
import com.happs.ximand.clothingtags.databinding.FragmentAddSecondStepBinding
import com.happs.ximand.clothingtags.viewmodel.AddTagSecondStepViewModel

class AddTagSecondStepFragment :
    BaseAddTagFragment<AddTagSecondStepViewModel, FragmentAddSecondStepBinding>(
        R.layout.fragment_add_second_step, R.menu.menu_add_second_step
    ) {

    companion object {
        @JvmStatic
        fun newInstance(): AddTagSecondStepFragment {
            return AddTagSecondStepFragment()
        }
    }

}