package com.example.testlibrarysong.presentation.ui.users

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.testlibrarysong.business.domain.singletons.PlaylistSongsSingleton
import com.example.testlibrarysong.business.usecases.GetAllUsersUseCase
import com.example.testlibrarysong.business.usecases.GetUsersByPlaylistUseCase

class UserListViewModel(
    getUsersByPlaylistUseCase: GetUsersByPlaylistUseCase,
    getAllUsersUseCase: GetAllUsersUseCase,
    val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val users = if (PlaylistSongsSingleton.playList == null) {
        getAllUsersUseCase.getAllUsers().asLiveData()
    } else {
        getUsersByPlaylistUseCase.getUsersByPlaylist(PlaylistSongsSingleton.playList!!).asLiveData()
    }

    override fun onCleared() {
        super.onCleared()
        PlaylistSongsSingleton.clear()
    }
}