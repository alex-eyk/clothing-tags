package com.happs.ximand.clothingtags.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.happs.ximand.clothingtags.FragmentNavigation
import com.happs.ximand.clothingtags.R
import com.happs.ximand.clothingtags.model.ClothingTagRepository
import com.happs.ximand.clothingtags.model.`object`.ClothingTag
import com.happs.ximand.clothingtags.view.tagadd.AddTagFirstStepFragment
import com.happs.ximand.clothingtags.view.tagedit.EditTagFirstStepFragment
import com.happs.ximand.clothingtags.viewmodel.`object`.RemoveConfirmationDialog

class AllClothingTagsViewModel(savedState: SavedStateHandle) : BaseViewModel(savedState) {

    val clothingTagsLiveData = MutableLiveData<List<ClothingTag>>()
    val removeConfirmation = MutableLiveData<Event<RemoveConfirmationDialog>>()

    init {
        updateTagsList()
    }

    fun updateTagsList() {
        clothingTagsLiveData.value = ClothingTagRepository.instance?.queryAll()
    }

    fun removeTag(tag: ClothingTag) {
        removeConfirmation.value = Event(RemoveConfirmationDialog(tag))
    }

    fun notifyRemovingConfirmed() {
        ClothingTagRepository.instance?.remove(removeConfirmation.value?.peekContent()?.tag!!)
        updateTagsList()
    }

    fun editTag(tag: ClothingTag) {
        FragmentNavigation.getInstance()
            .navigateToNewFragment(EditTagFirstStepFragment.newInstance(tag))
    }

    override fun notifyOptionsMenuItemClicked(id: Int): Boolean {
        if (id == R.id.menu_add) {
            FragmentNavigation.getInstance()
                .navigateToNewFragment(AddTagFirstStepFragment.newInstance())
            return true
        }
        return false
    }
}
