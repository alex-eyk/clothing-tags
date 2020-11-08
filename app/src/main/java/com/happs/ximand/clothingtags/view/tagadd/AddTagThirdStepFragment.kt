package com.happs.ximand.clothingtags.view.tagadd

import android.view.MenuItem
import com.happs.ximand.clothingtags.R
import com.happs.ximand.clothingtags.databinding.FragmentAddThirdStepBinding
import com.happs.ximand.clothingtags.view.BaseEditTagFragment
import com.happs.ximand.clothingtags.viewmodel.AddTagSharedViewModel
import com.happs.ximand.clothingtags.viewmodel.tagadd.AddTagThirdStepViewModel

class AddTagThirdStepFragment :
    BaseEditTagFragment<AddTagThirdStepViewModel, AddTagSharedViewModel, FragmentAddThirdStepBinding>(
        R.layout.fragment_add_third_step, R.menu.menu_add_third_step
    ) {

    companion object {
        @JvmStatic
        fun newInstance(): AddTagThirdStepFragment {
            return AddTagThirdStepFragment()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (super.onOptionsItemSelected(item)) {
            true
        } else {
            sharedViewModel!!.notifyOptionsMenuItemClicked(item.itemId)
        }
    }
}