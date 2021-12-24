package com.example.testlibrarysong.presentation.ui.songs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testlibrarysong.R
import com.example.testlibrarysong.business.domain.Song
import com.example.testlibrarysong.databinding.UserListFragmentItemBinding

class PlaylistSongsAdapter: ListAdapter<Song, SongViewHolder>(SongComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        return SongViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.user_list_fragment_item, parent, false))
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}

class SongViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = UserListFragmentItemBinding.bind(itemView)
    fun onBind(song: Song) {
        binding.apply {
            tvSongName.text = song.name
            tvSongName.isVisible = true
            tvFirstName.text = song.singerName
            tvLastName.text = song.singerLastName
            tvEMailOrDescription.text = song.description
            btnDetails.isVisible = true
            btnUsersByPL.setText(R.string.libraries_by_song)
            btnUsersByPL.isVisible = true
        }
    }
}

class SongComparator : DiffUtil.ItemCallback<Song>() {
    override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean {
        return oldItem.id == newItem.id
    }
}