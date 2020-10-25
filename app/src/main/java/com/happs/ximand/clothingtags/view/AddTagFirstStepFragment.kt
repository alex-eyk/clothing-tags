package com.happs.ximand.clothingtags.view

import android.content.Intent
import androidx.lifecycle.Observer
import com.happs.ximand.clothingtags.R
import com.happs.ximand.clothingtags.databinding.FragmentAddFirstStepBinding
import com.happs.ximand.clothingtags.viewmodel.AddTagFirstStepViewModel

class AddTagFirstStepFragment :
    BaseAddTagFragment<AddTagFirstStepViewModel, FragmentAddFirstStepBinding>(
        R.layout.fragment_add_first_step, R.menu.menu_add_first_step
    ) {

    companion object {
        const val IMAGE_GET_REQUEST_CODE = 1

        @JvmStatic
        fun newInstance(): AddTagFirstStepFragment {
            return AddTagFirstStepFragment()
        }
    }

    override fun onPreViewModelAttached(viewModel: AddTagFirstStepViewModel) {
        viewModel.selectImageEvent.observe(viewLifecycleOwner, Observer {
            val intent = Intent()
                .setType("image/*")
                .setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(intent, IMAGE_GET_REQUEST_CODE)
        })
        viewModel.cropImageEvent.observe(viewLifecycleOwner, Observer {
            val intent = Intent()

        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == IMAGE_GET_REQUEST_CODE) {

        }
    }
}