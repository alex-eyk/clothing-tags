package com.happs.ximand.clothingtags.view.tagedit

import android.os.Bundle
import com.happs.ximand.clothingtags.R
import com.happs.ximand.clothingtags.databinding.FragmentAddFirstStepBinding
import com.happs.ximand.clothingtags.model.`object`.ClothingTag
import com.happs.ximand.clothingtags.view.BaseEditTagFragment
import com.happs.ximand.clothingtags.viewmodel.EditTagSharedViewModel
import com.happs.ximand.clothingtags.viewmodel.tagedit.EditTagFirstStepViewModel

class EditTagFirstStepFragment :
    BaseEditTagFragment<EditTagFirstStepViewModel, EditTagSharedViewModel, FragmentAddFirstStepBinding>(
        R.layout.fragment_add_first_step,
        R.menu.menu_add_first_step
    ) {

    private var clothingTag: ClothingTag? = null

    companion object {
        @JvmStatic
        fun newInstance(clothingTag: ClothingTag): EditTagFirstStepFragment {
            val fragment = EditTagFirstStepFragment()
            fragment.clothingTag = clothingTag
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (clothingTag != null) {
            sharedViewModel?.setEditingTag(clothingTag!!)
        }
    }
}