package com.example.testlibrarysong.presentation.ui.selectusers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testlibrarysong.R
import com.example.testlibrarysong.business.domain.models.PlayList
import com.example.testlibrarysong.databinding.UserListFragmentItemBinding
import com.example.testlibrarysong.presentation.ui.playlists.PlaylistsComparator

class SelectUsersRecyclerPlaylistsAdapter : ListAdapter<PlayList, SelectUsersRecyclerPlaylistViewHolder>(PlaylistsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectUsersRecyclerPlaylistViewHolder {
        return SelectUsersRecyclerPlaylistViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.user_list_fragment_item, parent, false)
        )
    }

    override fun onBindViewHolder(holderSelectUsersRecycler: SelectUsersRecyclerPlaylistViewHolder, position: Int) {
        holderSelectUsersRecycler.bind(getItem(position))
    }
}

class SelectUsersRecyclerPlaylistViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = UserListFragmentItemBinding.bind(itemView)

    fun bind(playlist: PlayList) {
        binding.apply {
            tvSongName.text = playlist.name
            tvSongName.isVisible = true
            tvFirstName.visibility = View.GONE
            tvLastName.visibility = View.GONE
            tvEMailOrDescription.text = playlist.description
        }
    }

}