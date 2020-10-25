package com.happs.ximand.clothingtags.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.happs.ximand.clothingtags.FragmentNavigation
import com.happs.ximand.clothingtags.R
import com.happs.ximand.clothingtags.model.ClothingTagRepository
import com.happs.ximand.clothingtags.model.`object`.ClothingTag
import com.happs.ximand.clothingtags.view.AddTagFirstStepFragment

class AllClothingTagsViewModel(savedState: SavedStateHandle) : BaseViewModel(savedState) {

    var clothingTagsLiveData = MutableLiveData<List<ClothingTag>>()
        private set

    init {
        updateTagsList()
    }

    fun updateTagsList() {
        clothingTagsLiveData.value = ClothingTagRepository.instance?.queryAll()
    }

    fun removeTag(tag: ClothingTag) {

    }

    fun editTag(tag: ClothingTag) {

    }

    override fun notifyOptionsMenuItemClicked(id: Int): Boolean {
        if (id == R.id.menu_add) {
            FragmentNavigation.getInstance()
                .navigateToNewFragment(AddTagFirstStepFragment.newInstance());
            return true
        }
        return false
    }
}
