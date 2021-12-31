package com.example.testlibrarysong.presentation.ui.playlists

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testlibrarysong.business.usecases.GetPlaylistsUseCase

@Suppress("UNCHECKED_CAST")
class PlaylistsViewModelFactory(
    private val getPlaylistsUseCase: GetPlaylistsUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PlaylistsViewModel(getPlaylistsUseCase) as T
    }
}