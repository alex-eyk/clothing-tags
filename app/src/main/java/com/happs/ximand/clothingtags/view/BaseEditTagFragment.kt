package com.happs.ximand.clothingtags.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import com.happs.ximand.clothingtags.BR
import com.happs.ximand.clothingtags.viewmodel.BaseEditTagSharedViewModel
import com.happs.ximand.clothingtags.viewmodel.BaseViewModel

abstract class BaseEditTagFragment<VM : BaseViewModel, SVM : BaseEditTagSharedViewModel, D : ViewDataBinding>(
    layoutResId: Int,
    menuResId: Int
) :
    BaseFragment<VM, D>(layoutResId, menuResId) {

    protected var sharedViewModel: SVM? = null

    companion object {
        const val IMAGE_GET_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedViewModel = ViewModelProvider(
            activity!!.viewModelStore, SavedStateViewModelFactory(activity!!.application!!, this)
        ).get(getViewModelGenericClass(1))
    }

    override fun onViewDataBindingCreated(binding: D) {
        binding.setVariable(BR.sharedViewModel, sharedViewModel)
        onShareViewModelAttached(sharedViewModel!!)
    }

    open fun onShareViewModelAttached(sharedViewModel: SVM) {
        sharedViewModel.makeSnackbarEvent.observe(viewLifecycleOwner, Observer {
            makeSnackbarOnEvent(it)
        })
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (super.onOptionsItemSelected(item)) {
            true
        } else {
            sharedViewModel!!.notifyOptionsMenuItemClicked(item.itemId)
        }
    }
}
