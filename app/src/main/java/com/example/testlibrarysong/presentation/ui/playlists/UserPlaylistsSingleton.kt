package com.example.testlibrarysong.presentation.ui.playlists

import com.example.testlibrarysong.business.domain.User

object UserPlaylistsSingleton {
    var user: User? = null

    fun clear() {
        user = null
    }
}