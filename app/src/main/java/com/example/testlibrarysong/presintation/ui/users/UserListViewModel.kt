package com.example.testlibrarysong.presintation.ui.users

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testlibrarysong.business.domain.User
import com.example.testlibrarysong.business.usecases.GetUsersUseCase
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class UserListViewModel(
    private val getUsersUseCase: GetUsersUseCase,
    val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val users = MutableLiveData<List<User>>()

    fun getUsers() {
        viewModelScope.launch {
//            delay(100) // нужна задержка??
            users.value = getUsersUseCase.getUsers()
        }
    }
}