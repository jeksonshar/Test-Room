package com.example.testlibrarysong.presentation.ui.selectsongs

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.testlibrarysong.business.domain.PlayList
import com.example.testlibrarysong.business.domain.User
import com.example.testlibrarysong.business.usecases.GetPlaylistsAndUsersBySong
import kotlinx.coroutines.launch

class SelectSongViewModel(
    private val getPlaylistsAndUsersBySong: GetPlaylistsAndUsersBySong
) : ViewModel() {

    val songData = getPlaylistsAndUsersBySong.getAllSongs().asLiveData()
    val playlistsBySong = MutableLiveData<List<PlayList>?>()
    val usersBySong = MutableLiveData<List<User>?>()
    private val selectedSong = MutableLiveData<Int>()

    fun getPlaylistsBySong() {
        viewModelScope.launch {
            setPlaylistsBySong()
        }
    }

    fun getUsersBySong() {
        viewModelScope.launch {
            if (playlistsBySong.value == null) {
                setPlaylistsBySong()
            }
            usersBySong.value = getPlaylistsAndUsersBySong.getUsersBySong(playlistsBySong.value!!)
        }
    }

    fun selectSongId(songId: Int) {
        selectedSong.value = songId
        playlistsBySong.value = null
        usersBySong.value = null
    }

    private suspend fun setPlaylistsBySong() {
        playlistsBySong.value = getPlaylistsAndUsersBySong.getPlaylistsBySong(selectedSong.value ?: 1)
    }

}