package com.example.testlibrarysong.business.domain

object SongPlaylistsSingleton {
    var song: Song? = null

    fun clear() {
        song = null
    }
}