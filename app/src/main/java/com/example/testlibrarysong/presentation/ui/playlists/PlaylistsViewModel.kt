package com.example.testlibrarysong.presentation.ui.playlists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testlibrarysong.business.domain.models.PlayList
import com.example.testlibrarysong.business.domain.singletons.SongPlaylistsSingleton
import com.example.testlibrarysong.business.domain.singletons.UserPlaylistsSingleton
import com.example.testlibrarysong.business.usecases.GetPlaylistsUseCase
import kotlinx.coroutines.launch

class PlaylistsViewModel(
    private val getPlaylistsUseCase: GetPlaylistsUseCase
): ViewModel() {

    private var userId = UserPlaylistsSingleton.user?.id
    private val songId = SongPlaylistsSingleton.song?.id

    private val _playlists = MutableLiveData<List<PlayList>>()
    val playlists: LiveData<List<PlayList>> = _playlists

    fun getPlaylists() {
        if (SongPlaylistsSingleton.song == null) {
            viewModelScope.launch {
                _playlists.value = getPlaylistsUseCase.getPlaylistsByUser(userId ?: 1)
            }
        } else {
            viewModelScope.launch {
                _playlists.value = getPlaylistsUseCase.getPlaylistsBySong(songId ?: 1)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        SongPlaylistsSingleton.clear()
        UserPlaylistsSingleton.clear()
//        PlaylistSongsSingleton.clear()
    }
}