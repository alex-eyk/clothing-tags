package com.happs.ximand.clothingtags.view

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import com.happs.ximand.clothingtags.BR
import com.happs.ximand.clothingtags.viewmodel.AddTagSharedViewModel
import com.happs.ximand.clothingtags.viewmodel.BaseAddTagViewModel
import com.happs.ximand.clothingtags.viewmodel.BaseViewModel

abstract class BaseAddTagFragment<VM : BaseAddTagViewModel, D : ViewDataBinding>(
    layoutResId: Int,
    menuResId: Int
) :
    BaseFragment<VM, D>(layoutResId, menuResId) {

    protected var sharedViewModel: BaseViewModel? = null
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedViewModel = ViewModelProvider(
            activity!!.viewModelStore, SavedStateViewModelFactory(activity!!.application!!, this)
        ).get(AddTagSharedViewModel::class.java)
    }

    open override fun onViewDataBindingCreated(binding: D) {
        binding.setVariable(BR.sharedViewModel, sharedViewModel)
    }
}
