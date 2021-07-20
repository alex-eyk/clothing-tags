package com.happs.ximand.clothingtags

import android.app.Application
import com.happs.ximand.clothingtags.model.dao.ImagesDaoImpl
import com.happs.ximand.clothingtags.model.repository.ClothingTagRepository

class ClothingTagsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ClothingTagRepository.initialize(this, BuildConfig.VERSION_CODE)
        ImagesDaoImpl.initialize(this)
    }
}