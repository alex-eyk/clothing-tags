package com.happs.ximand.clothingtags.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

abstract class BaseViewModel(protected val savedState: SavedStateHandle) : ViewModel() {

    val makeSnackbar = MutableLiveData<Event<String>>()

    open fun notifyOptionsMenuItemClicked(id: Int): Boolean {
        return false
    }

}