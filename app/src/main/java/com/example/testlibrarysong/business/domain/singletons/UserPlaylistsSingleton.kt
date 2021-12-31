package com.example.testlibrarysong.business.domain.singletons

import com.example.testlibrarysong.business.domain.models.User

object UserPlaylistsSingleton {
    var user: User? = null

    fun clear() {
        user = null
    }
}