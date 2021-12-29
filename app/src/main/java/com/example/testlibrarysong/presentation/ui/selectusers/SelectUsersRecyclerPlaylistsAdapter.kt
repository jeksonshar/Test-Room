package com.example.testlibrarysong.presentation.ui.selectusers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testlibrarysong.R
import com.example.testlibrarysong.business.domain.PlayList
import com.example.testlibrarysong.databinding.UserListFragmentItemBinding
import com.example.testlibrarysong.presentation.ui.playlists.PlaylistComparator

class SelectUsersRecyclerPlaylistsAdapter : ListAdapter<PlayList, SelectUsersRecyclerPlaylistViewHolder>(PlaylistComparator())  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectUsersRecyclerPlaylistViewHolder {
        return SelectUsersRecyclerPlaylistViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.user_list_fragment_item, parent, false)
        )
    }

    override fun onBindViewHolder(holderSelectUsersRecycler: SelectUsersRecyclerPlaylistViewHolder, position: Int) {
        holderSelectUsersRecycler.onBind(getItem(position))
    }
}


class SelectUsersRecyclerPlaylistViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = UserListFragmentItemBinding.bind(itemView)
    fun onBind(playlist: PlayList) {
        binding.apply {
            tvSongName.text = playlist.name
            tvSongName.isVisible = true
            tvFirstName.visibility = View.GONE
            tvLastName.visibility = View.GONE
            tvEMailOrDescription.text = playlist.description
        }
    }

}