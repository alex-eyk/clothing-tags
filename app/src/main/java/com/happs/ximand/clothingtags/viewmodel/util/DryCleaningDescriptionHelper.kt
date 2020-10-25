package com.happs.ximand.clothingtags.viewmodel.util

import com.happs.ximand.clothingtags.R
import java.lang.IllegalArgumentException

object DryCleaningDescriptionHelper {
    private val idDescriptionResIdMap: Map<Int, Int> =
        object : HashMap<Int, Int>() {
            init {
                put(-1, 0)
                put(R.id.dry_cleaning_any, R.string.dry_cleaning_any)
                put(R.id.dry_cleaning_p, R.string.dry_cleaning_p)
                put(R.id.dry_cleaning_p_, R.string.dry_cleaning_p_)
                put(R.id.dry_cleaning_f, R.string.dry_cleaning_f)
                put(R.id.dry_cleaning_f_, R.string.dry_cleaning_f_)
                put(R.id.dry_cleaning_min_t, R.string.dry_cleaning_min_t)
                put(R.id.dry_clean_without_stream, R.string.dry_clean_without_stream)
                put(R.id.dry_cleaning_low_humidity, R.string.dry_cleaning_low_humidity)
                put(R.id.dry_cleaning_short_drying, R.string.dry_cleaning_short_drying)
                put(R.id.dry_cleaning_banned, R.string.dry_cleaning_banned)
            }
        }

    fun getDryCleaningDescriptionResById(id: Int): Int {
        val resId = idDescriptionResIdMap[id]
        return resId ?: throw IllegalArgumentException("Wrong id: $id")
    }
}