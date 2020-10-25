package com.happs.ximand.clothingtags.viewmodel.util

import com.happs.ximand.clothingtags.R
import java.lang.IllegalArgumentException

object IroningDescriptionHelper {
    private val idDescriptionResIdMap: Map<Int, Int> =
        object : HashMap<Int, Int>() {
            init {
                put(-1, 0)
                put(R.id.ironing_200, R.string.ironing_200)
                put(R.id.ironing_150, R.string.ironing_150)
                put(R.id.ironing_110, R.string.ironing_110)
                put(R.id.ironing_any, R.string.ironing_any)
                put(R.id.ironing_not_stream_off, R.string.ironing_not_stream_off)
                put(R.id.ironing_banned, R.string.ironing_no)
            }
        }

    fun getIroningDescriptionResById(id: Int): Int {
        val resId = idDescriptionResIdMap[id]
        return resId ?: throw IllegalArgumentException("Wrong id: $id")
    }

}