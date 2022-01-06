package com.example.testlibrarysong.presentation.ui.selectusers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testlibrarysong.R
import com.example.testlibrarysong.business.domain.models.Song
import com.example.testlibrarysong.databinding.UserListFragmentItemBinding
import com.example.testlibrarysong.presentation.ui.songs.SongComparator

class SelectUsersRecyclerSongsAdapter : ListAdapter<Song, SelectUsersRecyclerSongsHolder>(SongComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectUsersRecyclerSongsHolder {
        return SelectUsersRecyclerSongsHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.user_list_fragment_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SelectUsersRecyclerSongsHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class SelectUsersRecyclerSongsHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = UserListFragmentItemBinding.bind(itemView)

    fun bind(song: Song) {
        binding.apply {
            tvSongName.text = song.name
            tvSongName.isVisible = true
            tvFirstName.text = song.singerName
            tvLastName.text = song.singerLastName
        }
    }
}