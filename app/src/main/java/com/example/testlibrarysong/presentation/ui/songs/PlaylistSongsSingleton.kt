package com.example.testlibrarysong.presentation.ui.songs

import com.example.testlibrarysong.business.domain.PlayList

object PlaylistSongsSingleton {
    var playList: PlayList? = null

    fun clear() {
        playList = null
    }
}