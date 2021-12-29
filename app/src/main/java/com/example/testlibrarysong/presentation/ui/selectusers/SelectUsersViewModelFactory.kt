package com.example.testlibrarysong.presentation.ui.selectusers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testlibrarysong.business.usecases.GetSimilarDataByTwoUsers

@Suppress("UNCHECKED_CAST")
class SelectUsersViewModelFactory(
    private val getSimilarDataByTwoUsers: GetSimilarDataByTwoUsers
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SelectUsersViewModel(getSimilarDataByTwoUsers) as T
    }
}