package com.happs.ximand.clothingtags.viewmodel.util

import com.happs.ximand.clothingtags.R
import java.lang.IllegalArgumentException
import java.util.*

object WashingDescriptionHelper {
    private val idDescriptionResIdMap: Map<Int, Int> =
        object : HashMap<Int, Int>() {
            init {
                put(-1, 0)
                put(R.id.washing_normal, R.string.wash_normal_type)
                put(R.id.washing_moderate, R.string.wash_moderate_type)
                put(R.id.washing_delicate, R.string.wash_delicate_type)
                put(R.id.washing_hand, R.string.wash_hand_type)
                put(R.id.washing_banned, R.string.wash_banned_type)
            }
        }

    fun getWashingDescriptionResById(id: Int): Int {
        val resId = idDescriptionResIdMap[id]
        return resId ?: throw IllegalArgumentException("Wrong id: $id")
    }
}