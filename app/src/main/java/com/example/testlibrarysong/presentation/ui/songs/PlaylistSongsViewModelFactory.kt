package com.example.testlibrarysong.presentation.ui.songs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testlibrarysong.business.usecases.GetSongsUseCase

@Suppress("UNCHECKED_CAST")
class PlaylistSongsViewModelFactory(
    private val getSongsUseCase: GetSongsUseCase
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PlaylistSongsViewModel(getSongsUseCase) as T
    }
}