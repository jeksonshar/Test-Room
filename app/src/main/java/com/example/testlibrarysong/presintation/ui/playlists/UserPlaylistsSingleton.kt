package com.example.testlibrarysong.presintation.ui.playlists

import com.example.testlibrarysong.business.domain.User

object UserPlaylistsSingleton {
    var user: User? = null

    fun clear() {
        user = null
    }
}