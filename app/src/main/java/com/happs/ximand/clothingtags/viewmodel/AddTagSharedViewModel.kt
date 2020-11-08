package com.happs.ximand.clothingtags.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.happs.ximand.clothingtags.model.ClothingTagRepository

class AddTagSharedViewModel(savedState: SavedStateHandle) : BaseEditTagSharedViewModel(savedState) {

    override fun doSaveAction() {
        ClothingTagRepository.instance?.add(clothingTag)
    }

}