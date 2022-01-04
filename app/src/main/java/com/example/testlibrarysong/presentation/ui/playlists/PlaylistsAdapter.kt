package com.example.testlibrarysong.presentation.ui.playlists

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testlibrarysong.business.domain.models.PlayList
import com.example.testlibrarysong.databinding.UserListFragmentItemBinding

class PlaylistsAdapter(
    private val clickListener: PlaylistClickListener? = null
) : ListAdapter<PlayList, PlaylistsViewHolder>(PlaylistsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistsViewHolder {
        return PlaylistsViewHolder.from(parent, clickListener)
    }

    override fun onBindViewHolder(holder: PlaylistsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class PlaylistsViewHolder(private val binding: UserListFragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {

    private var listener: PlaylistClickListener? = null
    private var playlistHolder: PlayList? = null

    init {
        itemView.setOnClickListener {
            openPlaylist()
        }

        binding.apply {
            btnDetails.setOnClickListener {
                openPlaylist()
            }
            btnUsersByPL.setOnClickListener {
                listener?.let {
                    playlistHolder?.let { playList ->
                        it.openUsersByPlaylist(playList)
                    }
                }
            }
        }
    }

    fun bind(playlist: PlayList) {
        playlistHolder = playlist
        binding.apply {
            tvSongName.text = playlist.name
            tvSongName.isVisible = true
            tvFirstName.visibility = View.GONE
            tvLastName.visibility = View.GONE
            tvEMailOrDescription.text = playlist.description
            if (listener != null) {
                btnDetails.isVisible = true
                btnUsersByPL.isVisible = true
            }
        }
    }

    private fun openPlaylist() {
        listener?.let {
            playlistHolder?.let { playlist ->
                it.openSongsByPlaylist(playlist)
            }
        }
    }

    companion object {
        fun from(parent: ViewGroup, listener: PlaylistClickListener?): PlaylistsViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = UserListFragmentItemBinding.inflate(inflater, parent, false)
            val viewHolder = PlaylistsViewHolder(binding)
            viewHolder.listener = listener
            return viewHolder
        }
    }
}

class PlaylistsComparator : DiffUtil.ItemCallback<PlayList>() {
    override fun areItemsTheSame(oldItem: PlayList, newItem: PlayList): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: PlayList, newItem: PlayList): Boolean {
        return oldItem.id == newItem.id
    }
}