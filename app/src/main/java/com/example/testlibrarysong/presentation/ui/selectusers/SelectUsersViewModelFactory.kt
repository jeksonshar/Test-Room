package com.example.testlibrarysong.presentation.ui.selectusers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testlibrarysong.business.usecases.GetAllUsersUseCase
import com.example.testlibrarysong.business.usecases.GetPlaylistsByUserUseCase
import com.example.testlibrarysong.business.usecases.GetSimilarPlaylistsByTwoUsersUseCase
import com.example.testlibrarysong.business.usecases.GetSimilarSongsByPlaylistsUseCase

@Suppress("UNCHECKED_CAST")
class SelectUsersViewModelFactory(
    private val getSimilarSongsByPlaylistsUseCase: GetSimilarSongsByPlaylistsUseCase,
    private val getAllUsersUseCase: GetAllUsersUseCase,
    private val getPlaylistsByUserUseCase: GetPlaylistsByUserUseCase,
    private val getSimilarPlaylistsByTwoUsersUseCase: GetSimilarPlaylistsByTwoUsersUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SelectUsersViewModel(
            getSimilarSongsByPlaylistsUseCase,
            getAllUsersUseCase,
            getPlaylistsByUserUseCase,
            getSimilarPlaylistsByTwoUsersUseCase
        ) as T
    }
}