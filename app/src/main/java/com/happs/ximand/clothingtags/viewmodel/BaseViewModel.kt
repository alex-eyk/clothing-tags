package com.happs.ximand.clothingtags.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

abstract class BaseViewModel(protected val savedState: SavedStateHandle) :
    ViewModel() {

    val makeSnackbarEvent = MutableLiveData<Event<Int>>()

    open fun notifyOptionsMenuItemClicked(id: Int): Boolean {
        return false
    }

    protected fun <T> runCoroutine(liveData: MutableLiveData<T>, block: () -> T) {
        viewModelScope.launch {
            val result = block()
            liveData.postValue(result)
        }
    }

}