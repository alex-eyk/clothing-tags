package com.happs.ximand.clothingtags.view

import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.happs.ximand.clothingtags.R
import com.happs.ximand.clothingtags.databinding.FragmentAllClothingTagsBinding
import com.happs.ximand.clothingtags.model.`object`.ClothingTag
import com.happs.ximand.clothingtags.view.adapter.AllClothingRecyclerViewAdapter
import com.happs.ximand.clothingtags.view.customview.ContextMenuRecyclerView
import com.happs.ximand.clothingtags.view.customview.ContextMenuRecyclerView.RecyclerViewContextMenuInfo
import com.happs.ximand.clothingtags.viewmodel.AllClothingTagsViewModel


class AllClothingTagsFragment : BaseFragment<AllClothingTagsViewModel,
        FragmentAllClothingTagsBinding>(R.layout.fragment_all_clothing_tags, R.menu.menu_main) {

    companion object {

        const val EVENT_UPDATE_LIST = 1

        @JvmStatic
        fun newInstance(): AllClothingTagsFragment {
            return AllClothingTagsFragment()
        }
    }

    private var allClothingRecyclerView: ContextMenuRecyclerView? = null

    override fun onViewDataBindingCreated(binding: FragmentAllClothingTagsBinding) {
        allClothingRecyclerView = binding.allClothingRecyclerView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        allClothingRecyclerView?.layoutManager = LinearLayoutManager(context)
        registerForContextMenu(allClothingRecyclerView!!)
    }

    override fun onPreViewModelAttached(viewModel: AllClothingTagsViewModel) {
        viewModel.clothingTagsLiveData.observe(viewLifecycleOwner, Observer { initAdapter(it) })
    }

    private fun initAdapter(clothingTags: List<ClothingTag>) {
        val adapter =
            AllClothingRecyclerViewAdapter(
                clothingTags
            )
        adapter.editClickListener = { tag ->
            viewModel?.editTag(tag)
        }
        adapter.removeClickListener = { tag ->
            viewModel?.removeTag(tag)
        }
        allClothingRecyclerView?.adapter = adapter
    }

    override fun onCreateContextMenu(
        menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = activity!!.menuInflater
        inflater.inflate(R.menu.menu_item, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as RecyclerViewContextMenuInfo
        // handle menu item here
        return false
    }

    override fun onExternalEvent(eventId: Int) {
        if (eventId == EVENT_UPDATE_LIST) {
            viewModel?.updateTagsList()
        }
    }
}