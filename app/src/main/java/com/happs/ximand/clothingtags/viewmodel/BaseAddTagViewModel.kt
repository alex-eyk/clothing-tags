package com.happs.ximand.clothingtags.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.happs.ximand.clothingtags.FragmentNavigation
import com.happs.ximand.clothingtags.R

abstract class BaseAddTagViewModel(savedState: SavedStateHandle, private val numOfStep: Int) :
    BaseViewModel(savedState) {

    override fun notifyOptionsMenuItemClicked(id: Int): Boolean {
        if (id == R.id.menu_cancel) {
            FragmentNavigation.getInstance().navigateToFewFragmentsBack(numOfStep)
            return true
        } else if (id == R.id.menu_back) {
            FragmentNavigation.getInstance().navigateToPreviousFragment()
            return true
        }
        return false
    }

}