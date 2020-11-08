package com.happs.ximand.clothingtags.view.tagadd

import com.happs.ximand.clothingtags.R
import com.happs.ximand.clothingtags.databinding.FragmentAddFirstStepBinding
import com.happs.ximand.clothingtags.view.BaseEditTagFragment
import com.happs.ximand.clothingtags.viewmodel.AddTagSharedViewModel
import com.happs.ximand.clothingtags.viewmodel.tagadd.AddTagFirstStepViewModel

class AddTagFirstStepFragment :
    BaseEditTagFragment<AddTagFirstStepViewModel, AddTagSharedViewModel, FragmentAddFirstStepBinding>(
        R.layout.fragment_add_first_step, R.menu.menu_add_first_step
    ) {

    companion object {
        @JvmStatic
        fun newInstance(): AddTagFirstStepFragment {
            return AddTagFirstStepFragment()
        }
    }

}