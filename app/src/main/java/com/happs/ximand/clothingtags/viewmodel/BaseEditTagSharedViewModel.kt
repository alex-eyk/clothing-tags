package com.happs.ximand.clothingtags.viewmodel

import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.happs.ximand.clothingtags.FragmentNavigation
import com.happs.ximand.clothingtags.R
import com.happs.ximand.clothingtags.SingleLiveEvent
import com.happs.ximand.clothingtags.model.`object`.ClothingTag
import com.happs.ximand.clothingtags.model.`object`.exception.AllFieldsEmptyException
import com.happs.ximand.clothingtags.model.`object`.exception.EmptyTitleException
import com.happs.ximand.clothingtags.model.dao.ImagesDaoImpl
import com.happs.ximand.clothingtags.view.AllClothingTagsFragment

abstract class BaseEditTagSharedViewModel(savedState: SavedStateHandle) :
    BaseViewModel(savedState) {

    companion object {
        const val IMAGE_NONE = -1
    }

    protected var clothingTag = ClothingTag()
    val selectImageEvent = SingleLiveEvent<Void>()

    val title = MutableLiveData<String>()
    val description = MutableLiveData<String>()
    val imageLiveData = MutableLiveData<Bitmap?>()
    val washingType = MutableLiveData(-1)
    val washingMaximumTemp = MutableLiveData(-1)
    val whiteningType = MutableLiveData(-1)
    val ironingType = MutableLiveData(-1)
    val dryCleaningType = MutableLiveData(-1)
    val spinningType = MutableLiveData(-1)
    val dryingType = MutableLiveData(-1)
    val canNotBeTwisted = MutableLiveData<Boolean>(false)

    var attachedImageId = IMAGE_NONE

    open fun setEditingTag(tag: ClothingTag) {
        clothingTag = tag
    }

    override fun onOptionsMenuItemClicked(id: Int) {
        if (id == R.id.menu_save_tag) {
            addTag()
        }
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
        attachedImageId = IMAGE_NONE
    }

    private fun addTag() {
        try {
            saveImageIfAttached()
            initClothingTag()
            doSaveAction()
            navigateBackToAllTagsFragment()
            notifyAllTagsFragmentAboutNewTag()
        } catch (e: AllFieldsEmptyException) {
            makeSnackbarEvent.value = Event(R.string.invalid_num_of_not_empty_fields)
        } catch (e: EmptyTitleException) {
            makeSnackbarEvent.value = Event(R.string.title_is_empty)
        }
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
        if (title.value.isNullOrBlank()) {
            throw EmptyTitleException()
        }
        clothingTag = ClothingTag.Builder()
            .setTitle(title.value!!)
            .setDescription(description.value)
            .setImageId(attachedImageId)
            .setWashingType(washingType.value!!)
            .setWashingMaximumTemp(washingMaximumTemp.value!!)
            .setWhiteningType(whiteningType.value!!)
            .setIroningType(ironingType.value!!)
            .setDryCleaningType(dryCleaningType.value!!)
            .setSpinningType(spinningType.value!!)
            .setDryingType(dryingType.value!!)
            .setCanNotBeTwisted(canNotBeTwisted.value!!)
            .build()
    }

    protected abstract fun doSaveAction()

    private fun navigateBackToAllTagsFragment() {
        FragmentNavigation.getInstance().navigateToFewFragmentsBack(3)
    }

    private fun notifyAllTagsFragmentAboutNewTag() {
        val tag = AllClothingTagsFragment::class.simpleName!!
        FragmentNavigation.getInstance().notifyFragmentAboutExternalEvent(
            tag, AllClothingTagsFragment.EVENT_UPDATE_LIST
        )
    }

}