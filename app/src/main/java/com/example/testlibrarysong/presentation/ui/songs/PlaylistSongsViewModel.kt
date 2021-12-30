package com.example.testlibrarysong.presentation.ui.songs

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testlibrarysong.business.domain.PlaylistSongsSingleton
import com.example.testlibrarysong.business.domain.Song
import com.example.testlibrarysong.business.usecases.GetSongsUseCase
import kotlinx.coroutines.launch

class PlaylistSongsViewModel(
    private val getSongsUseCase: GetSongsUseCase
) : ViewModel() {

    val songs = MutableLiveData<List<Song>>()

    fun getSongs() {
        val playListId = PlaylistSongsSingleton.playList?.id
        viewModelScope.launch {
            songs.value = getSongsUseCase.getSongs(playListId ?: 1)
        }
    }

    override fun onCleared() {
        super.onCleared()
        PlaylistSongsSingleton.clear()
    }
}