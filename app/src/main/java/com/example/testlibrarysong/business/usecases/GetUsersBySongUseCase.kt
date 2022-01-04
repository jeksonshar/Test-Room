package com.example.testlibrarysong.business.usecases

import androidx.collection.arraySetOf
import com.example.testlibrarysong.business.domain.models.PlayList
import com.example.testlibrarysong.business.domain.models.User
import com.example.testlibrarysong.datasourse.room.MusicDataBase
import com.example.testlibrarysong.datasourse.room.mappers.DataBaseMappers

class GetUsersBySongUseCase(
    private val dataBase: MusicDataBase?
) {

    suspend fun getUsersBySong(playlists: List<PlayList>): List<User> {
        if (dataBase == null) {
            return emptyList()
        }
        val playListsId = mutableListOf<Int>()
        for (pl in playlists) {
            playListsId.add(pl.id)
        }

        val usersToPlaylists = dataBase.usersToPlaylistsDao().getUsersByPlaylists(playListsId)
        val usersSet = arraySetOf<User>()
        val usersList = arrayListOf<User>()
        for (e in usersToPlaylists) {
            val entity = dataBase.userDao().getUser(e.userId)
            usersSet.add(DataBaseMappers.mapToUser(entity))
        }
        usersSet.map {
            usersList.add(it)
        }
        return usersList
    }

}