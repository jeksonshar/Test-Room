package com.example.testlibrarysong.business.usecases

import com.example.testlibrarysong.business.domain.models.User
import com.example.testlibrarysong.datasourse.room.MusicDataBase
import com.example.testlibrarysong.datasourse.room.mappers.DataBaseMappers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map

class GetAllUsersUseCase(
    private val dataBase: MusicDataBase?
) {
    fun getAllUsers(): Flow<List<User>> {
        if (dataBase == null) {
            return emptyFlow()
        }

        return dataBase.userDao().getAllUsers().map {
            val users = mutableListOf<User>()
            for (entity in it) {
                users.add(DataBaseMappers.mapToUser(entity))
            }
            users
        }
    }
}