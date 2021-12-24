package com.example.testlibrarysong.presentation.ui.playlists

import com.example.testlibrarysong.business.domain.PlayList

interface PlaylistClickListener {
    fun openSongsByPlaylist(playlist: PlayList)
    fun openUsersByPlaylist(playlist: PlayList)
}