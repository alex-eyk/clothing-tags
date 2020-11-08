package com.happs.ximand.clothingtags.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.happs.ximand.clothingtags.FragmentNavigation
import com.happs.ximand.clothingtags.R

abstract class BaseEditTagViewModel(savedState: SavedStateHandle, private val numOfStep: Int) :
    BaseViewModel(savedState) {

    override fun notifyOptionsMenuItemClicked(id: Int): Boolean {
        return when (id) {
            R.id.menu_cancel -> {
                FragmentNavigation.getInstance().navigateToFewFragmentsBack(numOfStep)
                true
            }
            R.id.menu_back -> {
                FragmentNavigation.getInstance().navigateToPreviousFragment()
                true
            }
            R.id.menu_next -> {
                navigateToNextFragment()
                true
            }
            else -> false
        }
    }

    open fun navigateToNextFragment() {

    }

}