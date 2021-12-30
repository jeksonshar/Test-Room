package com.example.testlibrarysong.presentation.ui.selectusers

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.testlibrarysong.business.domain.PlayList
import com.example.testlibrarysong.business.domain.Song
import com.example.testlibrarysong.business.usecases.GetSimilarDataByTwoUsers
import kotlinx.coroutines.launch

class SelectUsersViewModel(
    private val getSimilarDataByTwoUsers: GetSimilarDataByTwoUsers
) : ViewModel() {

    val playlistsByUserFirst = MutableLiveData<List<PlayList>>()
    private val playlistsByUserSecond = MutableLiveData<List<PlayList>>()
    val usersData = getSimilarDataByTwoUsers.getAllUsers().asLiveData()
    val songsByPlaylists = MutableLiveData<List<Song>>()


    fun getFirstUserPlaylists(id: Int) {
        viewModelScope.launch {
            playlistsByUserFirst.value = getSimilarDataByTwoUsers.getPlaylistsByUser(id)
        }
    }

    fun getSecondUserPlaylists(id: Int) {
        viewModelScope.launch {
            playlistsByUserSecond.value = getSimilarDataByTwoUsers.getPlaylistsByUser(id)
        }
    }

    fun compareUsersPlayList(): List<PlayList> {
        return getSimilarDataByTwoUsers.compareUsersPlaylist(playlistsByUserFirst.value!!, playlistsByUserSecond.value!!)
    }

    fun showAllSongsByPlaylists() {
        val playlists = compareUsersPlayList()
        viewModelScope.launch {
            songsByPlaylists.value = getSimilarDataByTwoUsers.getSongsByPlaylists(playlists)
        }
    }


}