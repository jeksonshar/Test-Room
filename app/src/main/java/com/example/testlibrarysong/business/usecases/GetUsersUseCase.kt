package com.example.testlibrarysong.business.usecases

import com.example.testlibrarysong.business.domain.User
import com.example.testlibrarysong.datasourse.room.MusicDataBase
import com.example.testlibrarysong.datasourse.room.mappers.DataBaseMappers
import java.util.ArrayList

class GetUsersUseCase(private val dataBase: MusicDataBase? ) {

    suspend fun getUsers(): List<User> {
        if (dataBase == null) {
            return emptyList()
        }

        val entities = dataBase.songDao().getAllUsers()
        val users = ArrayList<User>()
        for (entity in entities) {
            users.add(DataBaseMappers.mapToUser(entity))
        }
        return users
    }
}