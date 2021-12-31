package com.example.testlibrarysong.presentation.ui.playlists

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testlibrarysong.R
import com.example.testlibrarysong.business.domain.models.PlayList
import com.example.testlibrarysong.databinding.UserListFragmentItemBinding

class PlaylistsAdapter(
    private val clickListener: PlaylistClickListener
) : ListAdapter<PlayList, PlaylistsViewHolder>(PlaylistsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistsViewHolder {
        return PlaylistsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.user_list_fragment_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PlaylistsViewHolder, position: Int) {
        holder.onBind(getItem(position))
        holder.itemView.setOnClickListener {
            openPlaylist(getItem(position))
        }
        holder.itemView.findViewById<AppCompatButton>(R.id.btnDetails).setOnClickListener {
            openPlaylist(getItem(position))
        }
        holder.itemView.findViewById<AppCompatButton>(R.id.btnUsersByPL).setOnClickListener {
            clickListener.openUsersByPlaylist(getItem(position))
        }
    }

    private fun openPlaylist(playlist: PlayList) {
        clickListener.openSongsByPlaylist(playlist)
    }
}

class PlaylistsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = UserListFragmentItemBinding.bind(itemView)
    fun onBind(playlist: PlayList) {
        binding.apply {
            tvSongName.text = playlist.name
            tvSongName.isVisible = true
            tvFirstName.visibility = View.GONE
            tvLastName.visibility = View.GONE
            tvEMailOrDescription.text = playlist.description
            btnDetails.isVisible = true
            btnUsersByPL.isVisible = true
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