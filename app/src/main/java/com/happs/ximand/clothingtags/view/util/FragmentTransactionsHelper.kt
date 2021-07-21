package com.happs.ximand.clothingtags.view.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.happs.ximand.clothingtags.R

object FragmentTransactionsHelper {

    private const val MAIN_CONTAINER: Int = R.id.main_container

    private const val ANIM_IN: Int = R.animator.fade_in
    private const val ANIM_OUT: Int = R.animator.fade_out

    fun replaceFragment(manager: FragmentManager, fragment: Fragment?, tag: String?) {
        manager.beginTransaction()
            .setCustomAnimations(
                ANIM_IN,
                ANIM_OUT,
                ANIM_IN,
                ANIM_OUT
            )
            .replace(MAIN_CONTAINER, fragment!!, tag)
            .addToBackStack(tag)
            .commitAllowingStateLoss()
        manager.executePendingTransactions()
    }

}