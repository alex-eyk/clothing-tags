package com.happs.ximand.clothingtags

import android.app.Application
import com.happs.ximand.clothingtags.model.ClothingTagRepository

class ClothingTagsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ClothingTagRepository.initialize(this, BuildConfig.VERSION_CODE)
    }
}