package com.happs.ximand.clothingtags.viewmodel.util

import com.happs.ximand.clothingtags.R
import java.lang.IllegalArgumentException
import java.util.HashMap

object MaxTemperatureDescriptionHelper {

    private val idDescriptionResIdMap: Map<Int, Int> =
        object : HashMap<Int, Int>() {
            init {
                put(-1, 0)
                put(R.id.washing_30, R.string._30)
                put(R.id.washing_45, R.string._45)
                put(R.id.washing_60, R.string._60)
                put(R.id.washing_75, R.string._75)
                put(R.id.washing_90, R.string._90)
            }
        }

    fun getMaxTemperatureResById(id: Int): Int {
        val resId = idDescriptionResIdMap[id]
        return resId ?: throw IllegalArgumentException("Wrong id: $id")
    }

}