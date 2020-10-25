package com.happs.ximand.clothingtags.model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ClothingTagSQLiteHelper(context: Context, version: Int) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, version) {

    companion object {
        const val DATABASE_NAME = "CLOTHING_TAGS"
        const val FIELD_ID = "ID"
        const val FIELD_TITLE = "TITLE"
        const val FIELD_IMAGE_PATH = "IMAGE_PATH"
        const val FIELD_DESCRIPTION = "DESC"
        const val FIELD_WASHING = "WASHING"
        const val FIELD_WASHING_MAX_TEMP = "WASH_MAX_TEMP"
        const val FIELD_WHITENING = "WHITENING"
        const val FIELD_IRONING = "IRONING"
        const val FIELD_DRY_CLEANING = "DRY_CLEANING"
        const val FIELD_SPINNING = "SPINNING"
        const val FIELD_DRYING = "DRYING"
        const val FIELD_CAN_BE_TWISTED = "TWIST"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
            "CREATE TABLE IF NOT EXISTS $DATABASE_NAME(" +
                    "$FIELD_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$FIELD_TITLE TEXT NOT NULL, " +
                    "$FIELD_IMAGE_PATH TEXT, " +
                    "$FIELD_DESCRIPTION TEXT, " +
                    "$FIELD_WASHING INTEGER DEFAULT 0, " +
                    "$FIELD_WASHING_MAX_TEMP INTEGER DEFAULT 0, " +
                    "$FIELD_WHITENING INTEGER DEFAULT 0, " +
                    "$FIELD_IRONING INTEGER DEFAULT 0, " +
                    "$FIELD_DRY_CLEANING INTEGER DEFAULT 0, " +
                    "$FIELD_SPINNING INTEGER DEFAULT 0, " +
                    "$FIELD_DRYING INTEGER DEFAULT 0," +
                    "$FIELD_CAN_BE_TWISTED INTEGER DEFAULT 0"
                    + ");"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $DATABASE_NAME")
    }

}