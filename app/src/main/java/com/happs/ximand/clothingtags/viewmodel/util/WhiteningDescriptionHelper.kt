package com.happs.ximand.clothingtags.viewmodel.util

import com.happs.ximand.clothingtags.R
import java.lang.IllegalArgumentException

object WhiteningDescriptionHelper {
    private val idDescriptionResIdMap: Map<Int, Int> =
        object : HashMap<Int, Int>() {
            init {
                put(-1, 0)
                put(R.id.whitening_any, R.string.whitening_any_type)
                put(R.id.whitening_without_cl, R.string.whitening_without_cl_type)
                put(R.id.whitening_banned, R.string.whitening_banned_type)
            }
        }

    fun getWhiteningDescriptionResById(id: Int): Int {
        val resId = idDescriptionResIdMap[id]
        return resId ?: throw IllegalArgumentException("Wrong id: $id")
    }

}