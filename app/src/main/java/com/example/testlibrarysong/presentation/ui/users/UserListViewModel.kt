package com.example.testlibrarysong.presentation.ui.users

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.testlibrarysong.business.domain.singletons.PlaylistSongsSingleton
import com.example.testlibrarysong.business.usecases.GetUsersUseCase

class UserListViewModel(
    getUsersUseCase: GetUsersUseCase,
    val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val users = if (PlaylistSongsSingleton.playList == null) {
        getUsersUseCase.getUsers().asLiveData()
    } else {
        getUsersUseCase.getUsers(PlaylistSongsSingleton.playList!!).asLiveData()
    }

    override fun onCleared() {
        super.onCleared()
        PlaylistSongsSingleton.clear()
    }
}