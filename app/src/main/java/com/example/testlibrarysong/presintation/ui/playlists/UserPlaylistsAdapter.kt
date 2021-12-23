package com.example.testlibrarysong.presintation.ui.playlists

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testlibrarysong.R
import com.example.testlibrarysong.business.domain.PlayList
import com.example.testlibrarysong.databinding.UserListFragmentItemBinding

class PlaylistAdapter: ListAdapter<PlayList, PlaylistViewHolder>(PlaylistComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        return PlaylistViewHolder(
            LayoutInflater.from(parent.context)
            .inflate(R.layout.user_list_fragment_item, parent, false))
    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}

class PlaylistViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = UserListFragmentItemBinding.bind(itemView)
    fun onBind(playlist: PlayList) {
        binding.tvSongName.text = playlist.name
        binding.tvSongName.isVisible = true
        binding.tvFirstName.visibility = View.GONE
        binding.tvLastName.visibility = View.GONE
        binding.tvEMailOrDescription.text = playlist.description
    }
}

class PlaylistComparator : DiffUtil.ItemCallback<PlayList>() {
    override fun areItemsTheSame(oldItem: PlayList, newItem: PlayList): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: PlayList, newItem: PlayList): Boolean {
        return oldItem.id == newItem.id
    }
}