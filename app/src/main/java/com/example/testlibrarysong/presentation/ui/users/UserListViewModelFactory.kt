package com.example.testlibrarysong.presentation.ui.users

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.testlibrarysong.business.usecases.GetAllUsersUseCase
import com.example.testlibrarysong.business.usecases.GetUsersByPlaylistUseCase

@Suppress("UNCHECKED_CAST")
class UserListViewModelFactory(
    private val getUsersByPlaylistUseCase: GetUsersByPlaylistUseCase,
    private val getAllUsersUseCase: GetAllUsersUseCase,
    owner: SavedStateRegistryOwner
) : AbstractSavedStateViewModelFactory(owner, null) {

    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return UserListViewModel(getUsersByPlaylistUseCase, getAllUsersUseCase, handle) as T
    }
}