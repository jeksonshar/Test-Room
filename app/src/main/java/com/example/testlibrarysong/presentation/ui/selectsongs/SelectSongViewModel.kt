package com.example.testlibrarysong.presentation.ui.selectsongs

import androidx.lifecycle.*
import com.example.testlibrarysong.business.domain.models.PlayList
import com.example.testlibrarysong.business.domain.models.User
import com.example.testlibrarysong.business.usecases.GetAllSongsUseCase
import com.example.testlibrarysong.business.usecases.GetPlaylistsBySongUseCase
import com.example.testlibrarysong.business.usecases.GetUsersBySongUseCase
import kotlinx.coroutines.launch

class SelectSongViewModel(
    private val getUsersBySongUseCase: GetUsersBySongUseCase,
    getAllSongsUseCase: GetAllSongsUseCase,
    private val getPlaylistsBySongUseCase: GetPlaylistsBySongUseCase
) : ViewModel() {

    val songData = getAllSongsUseCase.getAllSongs().asLiveData()
    private val _playlistsBySong = MutableLiveData<List<PlayList>?>()
    val playlistsBySong: LiveData<List<PlayList>?> = _playlistsBySong
    private val _usersBySong = MutableLiveData<List<User>?>()
    val usersBySong: LiveData<List<User>?> = _usersBySong
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
            _usersBySong.value = getUsersBySongUseCase.getUsersBySong(playlistsBySong.value!!)
        }
    }

    fun selectSongId(position: Int) {
        val songId = songData.value?.get(position)?.id ?: 1
        selectedSong.value = songId
        _playlistsBySong.value = null
        _usersBySong.value = null
    }

    private suspend fun setPlaylistsBySong() {
        _playlistsBySong.value = getPlaylistsBySongUseCase.getPlaylistsBySong(selectedSong.value ?: 1)
    }

}