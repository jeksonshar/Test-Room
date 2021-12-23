package com.example.testlibrarysong.presintation.ui.users

import com.example.testlibrarysong.business.domain.User

interface UserFragmentClickListener {

    fun openUsersPlaylists(user: User)
}