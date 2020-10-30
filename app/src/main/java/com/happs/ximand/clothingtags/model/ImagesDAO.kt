package com.happs.ximand.clothingtags.model

import android.graphics.Bitmap

interface ImagesDAO {
    fun loadImageById(id: Int): Bitmap?
    fun savePhoto(bmp: Bitmap): Int
}