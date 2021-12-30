package com.example.testlibrarysong.business.domain

object PlaylistSongsSingleton {
    var playList: PlayList? = null

    fun clear() {
        playList = null
    }
}