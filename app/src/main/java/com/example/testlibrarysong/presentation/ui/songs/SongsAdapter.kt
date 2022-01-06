package com.example.testlibrarysong.presentation.ui.songs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testlibrarysong.R
import com.example.testlibrarysong.business.domain.models.Song
import com.example.testlibrarysong.databinding.UserListFragmentItemBinding

class SongsAdapter(
    private val clickListener: SongClickListener
) : ListAdapter<Song, SongViewHolder>(SongComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        return SongViewHolder.from(parent, clickListener)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class SongViewHolder(private val binding: UserListFragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {

    private var listener: SongClickListener? = null
    private var songHolder: Song? = null

    init {
        itemView.setOnClickListener {
            openPlaylistsBySong()
        }
        binding.btnUsersByPL.setOnClickListener {
            openPlaylistsBySong()
        }
    }

    fun bind(song: Song) {
        songHolder = song
        binding.apply {
            tvSongName.text = song.name
            tvSongName.isVisible = true
            tvFirstName.text = song.singerName
            tvLastName.text = song.singerLastName
            btnUsersByPL.setText(R.string.libraries_by_song)
            btnUsersByPL.isVisible = true
        }
    }

    private fun openPlaylistsBySong() {
        listener.let {
            songHolder?.let { song ->
                it?.openPlaylistsBySong(song)
            }
        }
    }

    companion object {
        fun from(parent: ViewGroup, listener: SongClickListener?): SongViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = UserListFragmentItemBinding.inflate(inflater, parent, false)
            val viewHolder = SongViewHolder(binding)
            viewHolder.listener = listener
            return viewHolder
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