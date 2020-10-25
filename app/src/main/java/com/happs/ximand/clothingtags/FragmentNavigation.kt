package com.happs.ximand.clothingtags

import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.happs.ximand.clothingtags.view.BaseFragment
import com.happs.ximand.clothingtags.view.util.FragmentTransactionsHelper
import com.happs.ximand.clothingtags.viewmodel.BaseViewModel
import java.lang.ref.WeakReference

class FragmentNavigation private constructor(manager: FragmentManager) {

    companion object {
        private var instance: FragmentNavigation? = null

        @JvmStatic
        fun initialize(manager: FragmentManager) {
            instance = FragmentNavigation(manager)
        }

        @JvmStatic
        fun getInstance(): FragmentNavigation {
            if (instance != null) {
                return instance!!
            } else {
                throw IllegalStateException()
            }
        }

    }

    private val managerRef: WeakReference<FragmentManager> = WeakReference(manager)

    fun navigateToPreviousFragment() {
        val manager = managerRef.get()
        manager?.popBackStackImmediate()
            ?: throw NullPointerException("FragmentManager has null ref")
    }

    fun navigateToFewFragmentsBack(numOfFragments: Int) {
        for (i in 1..numOfFragments) {
            navigateToPreviousFragment()
        }
    }

    fun navigateToNewFragment(fragment: Fragment) {
        val manager = managerRef.get()
        if (manager != null) {
            FragmentTransactionsHelper.replaceFragment(
                manager, fragment, fragment.javaClass.simpleName
            )
        } else {
            throw IllegalStateException("FragmentManager was not initialized or destroyed")
        }
    }

    fun notifyFragmentAboutExternalEvent(tag: String, eventId: Int) {
        val manager = managerRef.get()
        if (manager != null) {
            val fragment = manager.findFragmentByTag(tag)
            (fragment as BaseFragment<*, *>).onExternalEvent(eventId)
        } else {
            throw IllegalStateException("FragmentManager was not initialized or destroyed")
        }
    }

    fun clearBackStack() {
        val manager = managerRef.get()
        manager?.popBackStack(
            null,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
    }

}