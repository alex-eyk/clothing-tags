package com.happs.ximand.clothingtags.model.dao

import android.graphics.Bitmap

interface ImagesDAO {
    fun loadImageById(id: Int): Bitmap?
    fun savePhoto(bmp: Bitmap): Int
}