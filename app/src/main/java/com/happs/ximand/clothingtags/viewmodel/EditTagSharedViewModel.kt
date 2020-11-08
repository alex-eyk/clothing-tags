package com.happs.ximand.clothingtags.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.happs.ximand.clothingtags.model.ClothingTagRepository
import com.happs.ximand.clothingtags.model.ImagesDaoImpl
import com.happs.ximand.clothingtags.model.`object`.ClothingTag

class EditTagSharedViewModel(savedState: SavedStateHandle) :
    BaseEditTagSharedViewModel(savedState) {

    override fun setEditingTag(tag: ClothingTag) {
        clothingTag = tag
        title.value = clothingTag.title
        description.value = clothingTag.description
        washingType.value = clothingTag.washingType
        washingMaximumTemp.value = clothingTag.washingMaximumTemp
        whiteningType.value = clothingTag.whiteningType
        ironingType.value = clothingTag.ironingType
        dryCleaningType.value = clothingTag.dryCleaningType
        spinningType.value = clothingTag.spinningType
        dryingType.value = clothingTag.dryingType
        canNotBeTwisted.value = !clothingTag.canBeTwisted
        attachedImageId = clothingTag.imageId!!
        if (attachedImageId != -1) {
            updatePhoto()
        }
    }

    override fun doSaveAction() {
        ClothingTagRepository.instance?.update(clothingTag)
    }

    private fun updatePhoto() {
        runCoroutine(imageLiveData) {
            ImagesDaoImpl.instance?.loadImageById(attachedImageId)
        }
    }
}