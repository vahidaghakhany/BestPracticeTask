package com.ramonapp.android.bestpracticetask.presentation.albums

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.ramonapp.android.bestpracticetask.databinding.ItemAlbumBinding
import com.ramonapp.android.bestpracticetask.domain.model.AlbumCombinationModel

class AlbumAdapter :
    ListAdapter<AlbumCombinationModel, AlbumAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder private constructor(
        private val binding: ItemAlbumBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AlbumCombinationModel) {
            binding.albumImg.load(item.image)
            binding.albumTitleTxt.text = item.albumTitle
            binding.photoTitleTxt.text = item.photoTitle
            binding.usernameTxt.text = item.username
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemAlbumBinding.inflate(inflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

}

class DiffCallback : DiffUtil.ItemCallback<AlbumCombinationModel>() {
    override fun areItemsTheSame(oldItem: AlbumCombinationModel, newItem: AlbumCombinationModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: AlbumCombinationModel, newItem: AlbumCombinationModel): Boolean {
        return oldItem.id == newItem.id
    }

}