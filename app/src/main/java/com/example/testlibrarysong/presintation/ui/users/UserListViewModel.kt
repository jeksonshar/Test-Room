package com.example.testlibrarysong.presintation.ui.users

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.testlibrarysong.business.usecases.GetUsersUseCase

class UserListViewModel(
    getUsersUseCase: GetUsersUseCase,
    val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val users = getUsersUseCase.getUsers().asLiveData()
}