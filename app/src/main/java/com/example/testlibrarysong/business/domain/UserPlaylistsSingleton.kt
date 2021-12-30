package com.example.testlibrarysong.business.domain

object UserPlaylistsSingleton {
    var user: User? = null

    fun clear() {
        user = null
    }
}