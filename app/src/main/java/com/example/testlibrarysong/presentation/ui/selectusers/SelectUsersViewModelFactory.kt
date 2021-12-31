package com.example.testlibrarysong.presentation.ui.selectusers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testlibrarysong.business.usecases.GetSimilarDataByTwoUsersUseCase

@Suppress("UNCHECKED_CAST")
class SelectUsersViewModelFactory(
    private val getSimilarDataByTwoUsersUseCase: GetSimilarDataByTwoUsersUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SelectUsersViewModel(getSimilarDataByTwoUsersUseCase) as T
    }
}