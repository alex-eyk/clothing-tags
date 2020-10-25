package com.happs.ximand.clothingtags.viewmodel.util

import com.happs.ximand.clothingtags.R
import java.lang.IllegalArgumentException

object SpinningDescriptionHelper {
    private val idDescriptionResIdMap: Map<Int, Int> =
        object : HashMap<Int, Int>() {
            init {
                put(-1, 0)
                put(R.id.spinning_normal, R.string.spinning_normal)
                put(R.id.spinning_low, R.string.spinning_low)
                put(R.id.spinning_banned, R.string.spinning_banned)
            }
        }

    fun getSpinningDescriptionResById(id: Int): Int {
        val resId = idDescriptionResIdMap[id]
        return resId ?: throw IllegalArgumentException("Wrong id: $id")
    }
}