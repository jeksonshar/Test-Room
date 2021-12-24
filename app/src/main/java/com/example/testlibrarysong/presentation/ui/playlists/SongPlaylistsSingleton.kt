package com.example.testlibrarysong.presentation.ui.playlists

import com.example.testlibrarysong.business.domain.Song

object SongPlaylistsSingleton {
    var song: Song? = null

    fun clear() {
        song = null
    }
}