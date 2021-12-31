package com.example.testlibrarysong.presentation.ui.songs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testlibrarysong.business.usecases.GetSongsByOnePlaylistUseCase

@Suppress("UNCHECKED_CAST")
class SongsViewModelFactory(
    private val getSongsByOnePlaylistUseCase: GetSongsByOnePlaylistUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SongsViewModel(getSongsByOnePlaylistUseCase) as T
    }
}