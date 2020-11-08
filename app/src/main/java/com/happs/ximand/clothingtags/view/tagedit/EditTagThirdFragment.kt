package com.happs.ximand.clothingtags.view.tagedit

import android.view.MenuItem
import com.happs.ximand.clothingtags.R
import com.happs.ximand.clothingtags.databinding.FragmentAddThirdStepBinding
import com.happs.ximand.clothingtags.view.BaseEditTagFragment
import com.happs.ximand.clothingtags.viewmodel.EditTagSharedViewModel
import com.happs.ximand.clothingtags.viewmodel.tagedit.EditTagThirdStepViewModel

class EditTagThirdFragment :
    BaseEditTagFragment<EditTagThirdStepViewModel, EditTagSharedViewModel, FragmentAddThirdStepBinding>(
        R.layout.fragment_add_third_step,
        R.menu.menu_add_third_step
    ) {

    companion object {
        @JvmStatic
        fun newInstance(): EditTagThirdFragment {
            return EditTagThirdFragment()
        }
    }

    //TODO: Повторяющийся код
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (super.onOptionsItemSelected(item)) {
            true
        } else {
            sharedViewModel!!.notifyOptionsMenuItemClicked(item.itemId)
        }
    }
}