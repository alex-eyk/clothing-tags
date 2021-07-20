package com.happs.ximand.clothingtags.viewmodel.util

import com.happs.ximand.clothingtags.R

object DryingDescriptionHelper {
    private val idDescriptionResIdMap: Map<Int, Int> =
        object : HashMap<Int, Int>() {
            init {
                put(-1, 0)
                put(R.id.drying_unfolded, R.string.drying_unfolded)
                put(R.id.drying_stretch, R.string.drying_stretch)
            }
        }

    fun getIroningDescriptionResById(id: Int): Int {
        val resId = idDescriptionResIdMap[id]
        return resId ?: throw IllegalArgumentException("Wrong id: $id")
    }
}