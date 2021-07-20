package com.happs.ximand.clothingtags.model.dao

import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*


class ImagesDaoImpl private constructor(private val app: Application) : ImagesDAO {

    companion object {
        var instance: ImagesDaoImpl? = null
            get() {
                if (field == null) {
                    throw IllegalStateException()
                }
                return field
            }
            private set

        fun initialize(app: Application) {
            instance = ImagesDaoImpl(app)
        }
    }

    fun getImageByUri(uri: Uri): Bitmap {
        val inputStream = app.contentResolver.openInputStream(uri)
        val srcBmp = BitmapFactory.decodeStream(inputStream)
        val squareBmp = createSquareBitmap(srcBmp)
        return Bitmap.createScaledBitmap(squareBmp, 128, 128, true)
    }

    private fun createSquareBitmap(srcBmp: Bitmap): Bitmap {
        return if (srcBmp.width >= srcBmp.height) {
            Bitmap.createBitmap(
                srcBmp, srcBmp.width / 2 - srcBmp.height / 2, 0, srcBmp.height, srcBmp.height
            )
        } else {
            Bitmap.createBitmap(
                srcBmp, 0, srcBmp.height / 2 - srcBmp.width / 2, srcBmp.width, srcBmp.width
            )
        }
    }

    override fun loadImageById(id: Int): Bitmap? {
        return try {
            val sd = createSdFile()
            BitmapFactory.decodeFile("${sd.absolutePath}/$id.jpg")
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override fun savePhoto(bmp: Bitmap): Int {
        return try {
            val sd = createSdFile()
            val id = getRandomNumber()
            val file = createJpegFile(sd, id)
            writeJpegFile(file, bmp)
            id
        } catch (e: Exception) {
            e.printStackTrace()
            0
        }
    }

    private fun createSdFile(): File {
        val sd = File(app.getExternalFilesDir(null)?.absolutePath + "/clothingtags")
        var success = true
        if (!sd.exists()) {
            success = sd.mkdir()
        }
        return if (success) sd else throw IOException()
    }

    private fun getRandomNumber(): Int {
        val random = Random()
        return random.nextInt(100000)
    }

    private fun createJpegFile(sd: File, id: Int): File {
        val file = File(sd, "$id.jpg")
        if (file.exists()) {
            file.delete()
        }
        return file
    }

    private fun writeJpegFile(file: File, bmp: Bitmap) {
        val out = FileOutputStream(file)
        bmp.compress(Bitmap.CompressFormat.JPEG, 90, out)
        out.flush()
        out.close()
    }

}