package com.example.testlibrarysong.business.domain.singletons

import com.example.testlibrarysong.business.domain.models.Song

object SongPlaylistsSingleton {
    var song: Song? = null

    fun clear() {
        song = null
    }
}