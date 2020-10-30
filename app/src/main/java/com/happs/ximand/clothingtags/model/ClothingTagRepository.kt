package com.happs.ximand.clothingtags.model

import android.app.Application
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteOpenHelper
import com.happs.ximand.clothingtags.model.`object`.ClothingTag
import java.util.*
import kotlin.collections.ArrayList

class ClothingTagRepository private constructor(app: Application, version: Int) :
    Repository<ClothingTag> {

    companion object {
        var instance: ClothingTagRepository? = null
            get() {
                if (field == null) {
                    throw IllegalStateException()
                }
                return field
            }
            private set

        fun initialize(app: Application, version: Int) {
            instance = ClothingTagRepository(app, version)
        }
    }

    private val dbHelper: SQLiteOpenHelper
    private val cursorToClothingTagMapper: Mapper<Cursor, ClothingTag>
    private val clothingTagToContentValuesMapper: Mapper<ClothingTag, ContentValues>

    init {
        dbHelper = ClothingTagSQLiteHelper(app, version)
        cursorToClothingTagMapper = CursorToClothingTagMapper()
        clothingTagToContentValuesMapper = ClothingTagToContentValuesMapper()
    }

    override fun add(item: ClothingTag) {
        add(Collections.singletonList(item))
    }

    override fun add(items: Iterable<ClothingTag>) {
        val database = dbHelper.writableDatabase
        try {
            database.beginTransaction()
            items.forEach {
                val contentValues = clothingTagToContentValuesMapper.map(it)
                val id = database.insert(
                    ClothingTagSQLiteHelper.DATABASE_NAME,
                    null, contentValues
                )
                it.id = id.toInt()
            }
            database.setTransactionSuccessful()
        } finally {
            database.endTransaction()
            database.close()
        }
    }

    override fun update(item: ClothingTag) {
        val database = dbHelper.writableDatabase
        try {
            database.beginTransaction()
            val contentValues = clothingTagToContentValuesMapper.map(item)
            database.update(
                ClothingTagSQLiteHelper.DATABASE_NAME, contentValues,
                "${ClothingTagSQLiteHelper.FIELD_ID} = ${item.id}", null
            )
            database.setTransactionSuccessful()
        } finally {
            database.endTransaction()
            database.close()
        }
    }

    override fun remove(item: ClothingTag) {
        val database = dbHelper.writableDatabase
        try {
            database.beginTransaction()
            database.execSQL(
                "DELETE FROM ${ClothingTagSQLiteHelper.DATABASE_NAME} " +
                        "WHERE ${ClothingTagSQLiteHelper.FIELD_ID} = ${item.id}"
            )
            database.setTransactionSuccessful()
        } finally {
            database.endTransaction()
            database.close()
        }
    }

    override fun queryAll(): List<ClothingTag> {
        val database = dbHelper.writableDatabase
        val list = ArrayList<ClothingTag>()
        try {
            database.beginTransaction()
            val cursor = database.rawQuery(
                "SELECT * FROM " + ClothingTagSQLiteHelper.DATABASE_NAME, arrayOf<String>()
            )
            if (cursor.moveToFirst()) {
                do {
                    list.add(cursorToClothingTagMapper.map(cursor))
                } while (cursor.moveToNext())
            }
            database.setTransactionSuccessful()
        } finally {
            database.endTransaction()
            database.close()
        }
        return list
    }

}