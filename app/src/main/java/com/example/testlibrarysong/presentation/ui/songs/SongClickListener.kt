package com.example.testlibrarysong.presentation.ui.songs

import com.example.testlibrarysong.business.domain.Song

interface SongClickListener {
    fun openPlaylistsBySong(song: Song)
}