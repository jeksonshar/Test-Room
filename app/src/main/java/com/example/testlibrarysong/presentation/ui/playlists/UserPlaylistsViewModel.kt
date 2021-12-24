package com.example.testlibrarysong.presentation.ui.playlists

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testlibrarysong.business.domain.PlayList
import com.example.testlibrarysong.business.usecases.GetPlaylistsUseCase
import com.example.testlibrarysong.presentation.ui.songs.PlaylistSongsSingleton
import kotlinx.coroutines.launch

class UserPlaylistsViewModel(
    private val getPlaylistsUseCase: GetPlaylistsUseCase
): ViewModel() {

    val playlists = MutableLiveData<List<PlayList>>()

    private var userId = UserPlaylistsSingleton.user?.id
    private val songId = SongPlaylistsSingleton.song?.id

    fun getPlaylists() {
        if (SongPlaylistsSingleton.song == null) {
            viewModelScope.launch {
                playlists.value = getPlaylistsUseCase.getPlaylistsByUser(userId ?: -1)
            }
        } else {
            viewModelScope.launch {
                playlists.value = getPlaylistsUseCase.getPlaylistsBySong(songId ?: -1)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        SongPlaylistsSingleton.clear()
    }
}