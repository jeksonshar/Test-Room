package com.example.testlibrarysong.presentation.ui.selectusers

import androidx.lifecycle.*
import com.example.testlibrarysong.business.domain.models.PlayList
import com.example.testlibrarysong.business.domain.models.Song
import com.example.testlibrarysong.business.usecases.GetAllUsersUseCase
import com.example.testlibrarysong.business.usecases.GetPlaylistsByUserUseCase
import com.example.testlibrarysong.business.usecases.GetSimilarPlaylistsByTwoUsersUseCase
import com.example.testlibrarysong.business.usecases.GetSimilarSongsByPlaylistsUseCase
import kotlinx.coroutines.launch

class SelectUsersViewModel(
    private val getSimilarSongsByPlaylistsUseCase: GetSimilarSongsByPlaylistsUseCase,
    getAllUsersUseCase: GetAllUsersUseCase,
    private val getPlaylistsByUserUseCase: GetPlaylistsByUserUseCase,
    private val getSimilarPlaylistsByTwoUsersUseCase: GetSimilarPlaylistsByTwoUsersUseCase
) : ViewModel() {

    private val playlistsByUserFirst = MutableLiveData<List<PlayList>>()
    private val playlistsByUserSecond = MutableLiveData<List<PlayList>>()
    val usersData = getAllUsersUseCase.getAllUsers().asLiveData()
    private val _songsByPlaylists = MutableLiveData<List<Song>>()
    val songsByPlaylists: LiveData<List<Song>> = _songsByPlaylists

    fun getFirstUserPlaylists(position: Int) {
        viewModelScope.launch {
            val id = usersData.value?.get(position)?.id ?: 1
            playlistsByUserFirst.value = getPlaylistsByUserUseCase.getPlaylistsByUser(id)
        }
    }

    fun getSecondUserPlaylists(position: Int) {
        viewModelScope.launch {
            val id = usersData.value?.get(position)?.id ?: 1
            playlistsByUserSecond.value = getPlaylistsByUserUseCase.getPlaylistsByUser(id)
        }
    }

    fun compareUsersPlayList(): List<PlayList> {
        return if (playlistsByUserFirst.value == null || playlistsByUserSecond.value == null) {
            emptyList()
        } else {
            getSimilarPlaylistsByTwoUsersUseCase.getSimilarPlaylistsByTwoUsers(playlistsByUserFirst.value!!, playlistsByUserSecond.value!!)
        }
    }

    fun showAllSongsByPlaylists() {
        val playlists = compareUsersPlayList()
        viewModelScope.launch {
            _songsByPlaylists.value = getSimilarSongsByPlaylistsUseCase.getSimilarSongsByPlaylists(playlists)
        }
    }
}