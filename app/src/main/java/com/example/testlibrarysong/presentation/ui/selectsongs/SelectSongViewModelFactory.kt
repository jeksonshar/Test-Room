package com.example.testlibrarysong.presentation.ui.selectsongs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testlibrarysong.business.usecases.GetPlaylistsAndUsersBySong

@Suppress("UNCHECKED_CAST")
class SelectSongViewModelFactory(
    private val getPlaylistsAndUsersBySong: GetPlaylistsAndUsersBySong
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SelectSongViewModel(getPlaylistsAndUsersBySong) as T
    }
}