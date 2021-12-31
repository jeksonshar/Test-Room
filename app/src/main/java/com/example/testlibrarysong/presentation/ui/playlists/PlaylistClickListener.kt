package com.example.testlibrarysong.presentation.ui.playlists

import com.example.testlibrarysong.business.domain.models.PlayList

interface PlaylistClickListener {

    fun openSongsByPlaylist(playlist: PlayList)
    fun openUsersByPlaylist(playlist: PlayList)

}