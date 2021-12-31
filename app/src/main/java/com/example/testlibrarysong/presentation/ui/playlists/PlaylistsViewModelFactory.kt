package com.example.testlibrarysong.presentation.ui.playlists

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testlibrarysong.business.usecases.GetPlaylistsBySongUseCase
import com.example.testlibrarysong.business.usecases.GetPlaylistsByUserUseCase

@Suppress("UNCHECKED_CAST")
class PlaylistsViewModelFactory(
    private val getPlaylistsBySongUseCase: GetPlaylistsBySongUseCase,
    private val getPlaylistsByUserUseCase: GetPlaylistsByUserUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PlaylistsViewModel(getPlaylistsBySongUseCase, getPlaylistsByUserUseCase) as T
    }
}