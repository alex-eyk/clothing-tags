package com.happs.ximand.clothingtags.viewmodel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.happs.ximand.clothingtags.FragmentNavigation
import com.happs.ximand.clothingtags.R
import com.happs.ximand.clothingtags.SingleLiveEvent
import com.happs.ximand.clothingtags.model.ClothingTagRepository
import com.happs.ximand.clothingtags.model.`object`.ClothingTag
import com.happs.ximand.clothingtags.view.AllClothingTagsFragment

class AddTagSharedViewModel(savedState: SavedStateHandle) : BaseViewModel(savedState) {


    val title = MutableLiveData<String>()
    val description = MutableLiveData<String>()
    val imageUri = MutableLiveData<Uri>()
    val washingType = MutableLiveData(-1)
    val washingMaximumTemp = MutableLiveData(-1)
    val whiteningType = MutableLiveData(-1)
    val ironingType = MutableLiveData(-1)
    val dryCleaningType = MutableLiveData(-1)
    val spinningType = MutableLiveData(-1)
    val dryingType = MutableLiveData(-1)
    val canNotBeTwisted = MutableLiveData<Boolean>(false)

    override fun notifyOptionsMenuItemClicked(id: Int): Boolean {
        if (id == R.id.menu_save_tag) {
            addTag()
            return true
        }
        return false
    }

    private fun addTag() {
        ClothingTagRepository.instance?.add(createClothingTagInstance())
        FragmentNavigation.getInstance().navigateToFewFragmentsBack(3)
        FragmentNavigation.getInstance().notifyFragmentAboutExternalEvent(
            AllClothingTagsFragment::class.simpleName!!,
            AllClothingTagsFragment.EVENT_UPDATE_LIST
        )
    }

    private fun createClothingTagInstance(): ClothingTag {
        val clothingTag = ClothingTag(title.value!!)
        clothingTag.description = description.value
        clothingTag.washingType = washingType.value!!
        clothingTag.washingMaximumTemp = washingMaximumTemp.value!!
        clothingTag.whiteningType = whiteningType.value!!
        clothingTag.ironingType = ironingType.value!!
        clothingTag.dryCleaningType = dryCleaningType.value!!
        clothingTag.spinningType = spinningType.value!!
        clothingTag.dryingType = dryingType.value!!
        clothingTag.canBeTwisted = !canNotBeTwisted.value!!
        return clothingTag
    }

}