package com.example.testlibrarysong.presentation.ui.users

import com.example.testlibrarysong.business.domain.models.User

interface UserClickListener {

    fun openUsersPlaylists(user: User)
}