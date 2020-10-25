package com.happs.ximand.clothingtags.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableBoolean
import androidx.recyclerview.widget.RecyclerView
import com.happs.ximand.clothingtags.R
import com.happs.ximand.clothingtags.databinding.ItemClothingTagBinding
import com.happs.ximand.clothingtags.databinding.ItemClothingTagDetailsBinding
import com.happs.ximand.clothingtags.model.`object`.ClothingTag

class AllClothingRecyclerViewAdapter(private val clothingTags: List<ClothingTag>) :
    RecyclerView.Adapter<AllClothingRecyclerViewAdapter.AllClothingViewHolder>() {

    var removeClickListener: ((tag: ClothingTag) -> Unit)? = null
    var editClickListener: ((tag: ClothingTag) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllClothingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemClothingTagBinding>(
            inflater, R.layout.item_clothing_tag, parent, false
        )
        return AllClothingViewHolder(
            binding
        )
    }

    override fun getItemCount(): Int {
        return clothingTags.size
    }

    override fun onBindViewHolder(holder: AllClothingViewHolder, position: Int) {
        holder.bind(clothingTags[position])
    }

    inner class AllClothingViewHolder(private val binding: ItemClothingTagBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val expanded = ObservableBoolean(false)

        fun bind(clothingTag: ClothingTag) {
            setOnViewStubInflateListener(clothingTag)
            binding.buttonMore.setOnClickListener { onMoreClick() }
            binding.buttonEdit.setOnClickListener { editClickListener?.invoke(clothingTag) }
            binding.buttonRemove.setOnClickListener { removeClickListener?.invoke(clothingTag) }
            binding.clothingTag = clothingTag
            binding.expanded = expanded
            binding.executePendingBindings()
        }

        private fun onMoreClick() {
            expanded.set(!expanded.get())
            inflateViewStubIfNecessary()
        }

        private fun inflateViewStubIfNecessary() {
            if (expanded.get() && !binding.detailsViewStub.isInflated) {
                binding.detailsViewStub.viewStub?.inflate()
            }
        }

        private fun setOnViewStubInflateListener(clothingTag: ClothingTag) {
            binding.detailsViewStub.setOnInflateListener { _, view ->
                val binding = DataBindingUtil.bind<ItemClothingTagDetailsBinding>(view)
                binding?.clothingTag = clothingTag
                binding?.expanded = expanded
                binding?.executePendingBindings()
            }
        }

    }
}