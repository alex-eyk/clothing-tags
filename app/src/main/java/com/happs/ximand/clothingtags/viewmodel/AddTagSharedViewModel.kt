package com.happs.ximand.clothingtags.viewmodel

import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.happs.ximand.clothingtags.FragmentNavigation
import com.happs.ximand.clothingtags.R
import com.happs.ximand.clothingtags.SingleLiveEvent
import com.happs.ximand.clothingtags.model.ClothingTagRepository
import com.happs.ximand.clothingtags.model.ImagesDaoImpl
import com.happs.ximand.clothingtags.model.`object`.ClothingTag
import com.happs.ximand.clothingtags.view.AllClothingTagsFragment

class AddTagSharedViewModel(savedState: SavedStateHandle) : BaseViewModel(savedState) {

    private val clothingTag = ClothingTag()

    val selectImageEvent = SingleLiveEvent<Void>()

    val title = MutableLiveData<String>()
    val description = MutableLiveData<String>()
    val imageLiveData = MutableLiveData<Bitmap>()
    val washingType = MutableLiveData(-1)
    val washingMaximumTemp = MutableLiveData(-1)
    val whiteningType = MutableLiveData(-1)
    val ironingType = MutableLiveData(-1)
    val dryCleaningType = MutableLiveData(-1)
    val spinningType = MutableLiveData(-1)
    val dryingType = MutableLiveData(-1)
    val canNotBeTwisted = MutableLiveData<Boolean>(false)

    var notEmptyFieldsCounter = 0

    var attachedImageId = -1

    override fun notifyOptionsMenuItemClicked(id: Int): Boolean {
        if (id == R.id.menu_save_tag) {
            addTag()
            return true
        }
        return false
    }

    fun selectImage() {
        selectImageEvent.call()
    }

    fun notifyImageSelected(uri: Uri?, selected: Boolean) {
        if (uri != null) {
            runCoroutine(imageLiveData) {
                val bitmap = ImagesDaoImpl.instance!!.getImageByUri(uri)
                makeSnackbarEvent.value = Event(R.string.image_was_selected)
                return@runCoroutine bitmap
            }
        } else if (!selected) {
            makeSnackbarEvent.value = Event(R.string.image_was_not_selected)
        } else {
            Log.e(
                javaClass.simpleName, "Image was selected, but uri is null",
                NullPointerException()
            )
        }
    }

    fun cancelAttaching() {
        if (imageLiveData.value != null) {
            imageLiveData.value = null
            makeSnackbarEvent.value = Event(R.string.image_was_deleted)
        }
    }

    private fun addTag() {
        if (validateTitle()) {
            saveImageIfAttached()
            initClothingTag()
            if (validateNotEmptyFieldsNum()) {
                ClothingTagRepository.instance?.add(clothingTag)
                navigateBackToAllTagsFragment()
                notifyAllTagsFragmentAboutNewTag()
            }
        }
    }

    private fun validateTitle(): Boolean {
        if (title.value.isNullOrEmpty()) {
            makeSnackbarEvent.value = Event(R.string.title_is_empty)
            return false
        }
        return true
    }

    private fun validateNotEmptyFieldsNum(): Boolean {
        if (notEmptyFieldsCounter == 0) {
            makeSnackbarEvent.value = Event(R.string.invalid_num_of_not_empty_fields)
            return false
        }
        return true
    }

    private fun saveImageIfAttached() {
        if (imageLiveData.value != null) {
            attachedImageId = ImagesDaoImpl.instance?.savePhoto(imageLiveData.value!!)!!
            if (attachedImageId == 0) {
                makeSnackbarEvent.value = Event(R.string.error_save_image)
            }
        }
    }

    private fun initClothingTag() {
        initTitle()
        initDescription()
        initImageId()
        initWashingType()
        initWashingMaximumTemp()
        initWhiteningType()
        initIroningType()
        initDryCleaningType()
        initSpinningType()
        initDryingType()
        initCanNotBeTwisted()
        validateNotEmptyFieldsNum()
    }

    private fun navigateBackToAllTagsFragment() {
        FragmentNavigation.getInstance().navigateToFewFragmentsBack(3)
    }

    private fun notifyAllTagsFragmentAboutNewTag() {
        val tag = AllClothingTagsFragment::class.simpleName!!
        FragmentNavigation.getInstance().notifyFragmentAboutExternalEvent(
            tag, AllClothingTagsFragment.EVENT_UPDATE_LIST
        )
    }

    private fun initTitle() {
        clothingTag.title = title.value!!
    }

    private fun initDescription() {
        clothingTag.description = description.value
    }

    private fun initImageId() {
        clothingTag.imageId = attachedImageId
    }

    private fun initWashingType() {
        clothingTag.washingType = washingType.value!!
        if (washingType.value != -1) {
            notEmptyFieldsCounter++
        }
    }

    private fun initWashingMaximumTemp() {
        clothingTag.washingMaximumTemp = washingMaximumTemp.value!!
        if (washingMaximumTemp.value != -1) {
            notEmptyFieldsCounter++
        }
    }

    private fun initWhiteningType() {
        clothingTag.whiteningType = whiteningType.value!!
        if (whiteningType.value != -1) {
            notEmptyFieldsCounter++
        }
    }

    private fun initIroningType() {
        clothingTag.ironingType = ironingType.value!!
        if (ironingType.value != -1) {
            notEmptyFieldsCounter++
        }
    }

    private fun initDryCleaningType() {
        clothingTag.dryCleaningType = dryCleaningType.value!!
        if (dryCleaningType.value != -1) {
            notEmptyFieldsCounter++
        }
    }

    private fun initSpinningType() {
        clothingTag.spinningType = spinningType.value!!
        if (spinningType.value != -1) {
            notEmptyFieldsCounter++
        }
    }

    private fun initDryingType() {
        clothingTag.dryingType = dryingType.value!!
        if (dryingType.value != -1) {
            notEmptyFieldsCounter++
        }
    }

    private fun initCanNotBeTwisted() {
        clothingTag.canBeTwisted = !canNotBeTwisted.value!!
        if (canNotBeTwisted.value != false) {
            notEmptyFieldsCounter++
        }
    }

}