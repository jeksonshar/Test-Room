package com.example.testlibrarysong.presintation.ui.playlists

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testlibrarysong.business.domain.PlayList
import com.example.testlibrarysong.business.usecases.GetPlaylistsUseCase
import kotlinx.coroutines.launch

class UserPlaylistsViewModel(
    private val getPlaylistsUseCase: GetPlaylistsUseCase
): ViewModel() {

    val playlists = MutableLiveData<List<PlayList>>()

    fun getPlaylists() {
        val userId = UserPlaylistsSingleton.user?.id
        viewModelScope.launch {
            playlists.value = getPlaylistsUseCase.getPlaylists(userId ?: -1)
        }
    }

    override fun onCleared() {
        super.onCleared()
        UserPlaylistsSingleton.clear()
    }
}