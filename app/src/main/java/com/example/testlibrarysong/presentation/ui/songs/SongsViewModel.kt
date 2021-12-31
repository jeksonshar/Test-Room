package com.example.testlibrarysong.presentation.ui.songs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testlibrarysong.business.domain.singletons.PlaylistSongsSingleton
import com.example.testlibrarysong.business.domain.models.Song
import com.example.testlibrarysong.business.usecases.GetSongsByOnePlaylistUseCase
import kotlinx.coroutines.launch

class SongsViewModel(
    private val getSongsByOnePlaylistUseCase: GetSongsByOnePlaylistUseCase
) : ViewModel() {

    val playListId = PlaylistSongsSingleton.playList?.id

    private val _songs = MutableLiveData<List<Song>>()
    val songs: LiveData<List<Song>> = _songs

    fun getSongs() {
        viewModelScope.launch {
            _songs.value = getSongsByOnePlaylistUseCase.getSongsByOnePlaylist(playListId ?: 1)
        }
    }

    override fun onCleared() {
        super.onCleared()
        PlaylistSongsSingleton.clear()
    }
}