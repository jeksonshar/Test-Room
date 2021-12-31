package com.example.testlibrarysong.presentation.ui.songs

import com.example.testlibrarysong.business.domain.models.Song

interface SongClickListener {
    fun openPlaylistsBySong(song: Song)
}