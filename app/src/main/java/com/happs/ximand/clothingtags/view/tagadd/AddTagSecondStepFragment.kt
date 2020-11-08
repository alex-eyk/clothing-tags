package com.happs.ximand.clothingtags.view.tagadd

import com.happs.ximand.clothingtags.R
import com.happs.ximand.clothingtags.databinding.FragmentAddSecondStepBinding
import com.happs.ximand.clothingtags.view.BaseEditTagFragment
import com.happs.ximand.clothingtags.viewmodel.AddTagSharedViewModel
import com.happs.ximand.clothingtags.viewmodel.tagadd.AddTagSecondStepViewModel

class AddTagSecondStepFragment :
    BaseEditTagFragment<AddTagSecondStepViewModel, AddTagSharedViewModel, FragmentAddSecondStepBinding>(
        R.layout.fragment_add_second_step, R.menu.menu_add_second_step
    ) {

    companion object {
        @JvmStatic
        fun newInstance(): AddTagSecondStepFragment {
            return AddTagSecondStepFragment()
        }
    }

}