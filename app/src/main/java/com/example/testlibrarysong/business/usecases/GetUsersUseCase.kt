package com.example.testlibrarysong.business.usecases

import com.example.testlibrarysong.business.domain.PlayList
import com.example.testlibrarysong.business.domain.User
import com.example.testlibrarysong.datasourse.room.MusicDataBase
import com.example.testlibrarysong.datasourse.room.mappers.DataBaseMappers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import java.util.*

class GetUsersUseCase(private val dataBase: MusicDataBase?) {

    fun getUsers(): Flow<List<User>> {
        if (dataBase == null) {
            return emptyFlow()
        }


        return dataBase.songDao().getAllUsers().map {
            val users = ArrayList<User>()
            for (entity in it) {
                users.add(DataBaseMappers.mapToUser(entity))
            }
            users
        }
    }

    fun getUsers(playList: PlayList): Flow<List<User>> {
        if (dataBase == null) {
            return emptyFlow()
        }

        return dataBase.songDao().getUsersByPL(playList.id).map {
            val entities = it.users
            val users = ArrayList<User>()

            for (entity in entities) {
                users.add(DataBaseMappers.mapToUser(entity))
            }
            users
        }
    }
}