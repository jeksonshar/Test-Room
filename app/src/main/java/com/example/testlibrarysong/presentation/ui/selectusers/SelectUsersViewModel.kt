package com.example.testlibrarysong.presentation.ui.selectusers

import androidx.lifecycle.*
import com.example.testlibrarysong.business.domain.models.PlayList
import com.example.testlibrarysong.business.domain.models.Song
import com.example.testlibrarysong.business.usecases.GetSimilarDataByTwoUsersUseCase
import kotlinx.coroutines.launch

class SelectUsersViewModel(
    private val getSimilarDataByTwoUsersUseCase: GetSimilarDataByTwoUsersUseCase
) : ViewModel() {

    private val playlistsByUserFirst = MutableLiveData<List<PlayList>>()
    private val playlistsByUserSecond = MutableLiveData<List<PlayList>>()
    val usersData = getSimilarDataByTwoUsersUseCase.getAllUsers().asLiveData()
    private val _songsByPlaylists = MutableLiveData<List<Song>>()
    val songsByPlaylists: LiveData<List<Song>> = _songsByPlaylists

    fun getFirstUserPlaylists(position: Int) {
        viewModelScope.launch {
            val id = usersData.value?.get(position)?.id ?: 1
            playlistsByUserFirst.value = getSimilarDataByTwoUsersUseCase.getPlaylistsByUser(id)
        }
    }

    fun getSecondUserPlaylists(position: Int) {
        viewModelScope.launch {
            val id = usersData.value?.get(position)?.id ?: 1
            playlistsByUserSecond.value = getSimilarDataByTwoUsersUseCase.getPlaylistsByUser(id)
        }
    }

    fun compareUsersPlayList(): List<PlayList> {
        return if (playlistsByUserFirst.value == null || playlistsByUserSecond.value == null) {
            emptyList()
        } else {
            getSimilarDataByTwoUsersUseCase.compareUsersPlaylist(playlistsByUserFirst.value!!, playlistsByUserSecond.value!!)
        }
    }

    fun showAllSongsByPlaylists() {
        val playlists = compareUsersPlayList()
        viewModelScope.launch {
            _songsByPlaylists.value = getSimilarDataByTwoUsersUseCase.getSongsByPlaylists(playlists)
        }
    }
}