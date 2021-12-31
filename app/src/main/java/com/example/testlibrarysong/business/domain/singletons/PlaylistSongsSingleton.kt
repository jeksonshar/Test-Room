package com.example.testlibrarysong.business.domain.singletons

import com.example.testlibrarysong.business.domain.models.PlayList

object PlaylistSongsSingleton {
    var playList: PlayList? = null

    fun clear() {
        playList = null
    }
}