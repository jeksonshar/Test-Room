package com.example.testlibrarysong.presentation.ui.users

import androidx.lifecycle.*
import com.example.testlibrarysong.business.domain.User
import com.example.testlibrarysong.business.usecases.GetUsersUseCase
import com.example.testlibrarysong.presentation.ui.songs.PlaylistSongsSingleton

class UserListViewModel(
    private val getUsersUseCase: GetUsersUseCase,
    val savedStateHandle: SavedStateHandle
) : ViewModel() {

    lateinit var users: LiveData<List<User>>

    fun getUsers() {
        users = if (PlaylistSongsSingleton.playList == null) {
            getUsersUseCase.getUsers().asLiveData()
        } else {
            getUsersUseCase.getUsers(PlaylistSongsSingleton.playList!!).asLiveData()
        }
    }

    override fun onCleared() {
        super.onCleared()
        PlaylistSongsSingleton.clear()
    }
}