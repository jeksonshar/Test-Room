package com.example.testlibrarysong.presentation.ui.selectsongs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testlibrarysong.business.usecases.GetAllSongsUseCase
import com.example.testlibrarysong.business.usecases.GetPlaylistsBySongUseCase
import com.example.testlibrarysong.business.usecases.GetUsersBySongUseCase

@Suppress("UNCHECKED_CAST")
class SelectSongViewModelFactory(
    private val getUsersBySongUseCase: GetUsersBySongUseCase,
    private val getAllSongsUseCase: GetAllSongsUseCase,
    private val getPlaylistsBySongUseCase: GetPlaylistsBySongUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SelectSongViewModel(getUsersBySongUseCase, getAllSongsUseCase, getPlaylistsBySongUseCase) as T
    }
}