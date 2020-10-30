package com.happs.ximand.clothingtags.model

import android.content.ContentValues
import com.happs.ximand.clothingtags.model.`object`.ClothingTag

class ClothingTagToContentValuesMapper : Mapper<ClothingTag, ContentValues> {

    override fun map(from: ClothingTag): ContentValues {
        val contentValues = ContentValues()
        contentValues.put(ClothingTagSQLiteHelper.FIELD_TITLE, from.title)
        contentValues.put(ClothingTagSQLiteHelper.FIELD_DESCRIPTION, from.description)
        contentValues.put(ClothingTagSQLiteHelper.FIELD_IMAGE_ID, from.imageId)
        contentValues.put(ClothingTagSQLiteHelper.FIELD_WASHING, from.washingType)
        contentValues.put(ClothingTagSQLiteHelper.FIELD_WASHING_MAX_TEMP, from.washingMaximumTemp)
        contentValues.put(ClothingTagSQLiteHelper.FIELD_WHITENING, from.whiteningType)
        contentValues.put(ClothingTagSQLiteHelper.FIELD_IRONING, from.ironingType)
        contentValues.put(ClothingTagSQLiteHelper.FIELD_DRY_CLEANING, from.dryCleaningType)
        contentValues.put(ClothingTagSQLiteHelper.FIELD_SPINNING, from.spinningType)
        contentValues.put(ClothingTagSQLiteHelper.FIELD_DRYING, from.dryingType)
        contentValues.put(
            ClothingTagSQLiteHelper.FIELD_CAN_BE_TWISTED,
            if (from.canBeTwisted) 1 else 0
        )
        return contentValues
    }

}