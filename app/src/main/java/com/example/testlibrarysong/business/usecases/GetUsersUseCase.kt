package com.example.testlibrarysong.business.usecases

import com.example.testlibrarysong.business.domain.User
import com.example.testlibrarysong.datasourse.room.SongDataBase
import com.example.testlibrarysong.datasourse.room.mappers.DataBaseMappers
import java.util.ArrayList

class GetUsersUseCase(private val dataBase: SongDataBase) {

    suspend fun getUsers(): List<User> {
        val entities = dataBase.songDao().getAllUsers()
        val users = ArrayList<User>()
        for (entity in entities) {
            users.add(DataBaseMappers.mapToUser(entity))
        }
        return users
    }
}