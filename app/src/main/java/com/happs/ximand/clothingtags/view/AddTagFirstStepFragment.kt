package com.happs.ximand.clothingtags.view

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.Observer
import com.happs.ximand.clothingtags.R
import com.happs.ximand.clothingtags.databinding.FragmentAddFirstStepBinding
import com.happs.ximand.clothingtags.viewmodel.AddTagFirstStepViewModel
import com.happs.ximand.clothingtags.viewmodel.AddTagSharedViewModel

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

    override fun onShareViewModelAttached(sharedViewModel: AddTagSharedViewModel) {
        super.onShareViewModelAttached(sharedViewModel)
        sharedViewModel.selectImageEvent.observe(viewLifecycleOwner, Observer {
            val intent = Intent()
                .setType("image/*")
                .setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(intent, IMAGE_GET_REQUEST_CODE)
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == IMAGE_GET_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                sharedViewModel?.notifyImageSelected(data?.data, true)
            } else {
                sharedViewModel?.notifyImageSelected(null, false)
            }
        }
    }
}