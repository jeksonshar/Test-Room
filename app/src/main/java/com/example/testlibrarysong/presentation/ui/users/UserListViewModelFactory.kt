package com.example.testlibrarysong.presentation.ui.users

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.testlibrarysong.business.usecases.GetUsersUseCase

@Suppress("UNCHECKED_CAST")
class UserListViewModelFactory(
    private val getUsersUseCase: GetUsersUseCase,
    owner: SavedStateRegistryOwner
): AbstractSavedStateViewModelFactory(owner, null) {

    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return UserListViewModel(getUsersUseCase, handle) as T
    }
}