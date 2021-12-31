package com.example.testlibrarysong.business.usecases

import com.example.testlibrarysong.business.domain.models.PlayList
import com.example.testlibrarysong.business.domain.models.Song
import com.example.testlibrarysong.business.domain.models.User
import com.example.testlibrarysong.datasourse.room.MusicDataBase
import com.example.testlibrarysong.datasourse.room.mappers.DataBaseMappers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map

class GetPlaylistsAndUsersBySongUseCase(
    private val dataBase: MusicDataBase?
) : GetPlaylistsUseCase(dataBase) {

    suspend fun getUsersBySong(playlists: List<PlayList>): List<User> {
        if (dataBase == null) {
            return emptyList()
        }
        val playListsId = mutableListOf<Int>()
        for (pl in playlists) {
            playListsId.add(pl.id)
        }

        val usersToPlaylists = dataBase.songDao().getUsersByPlaylists(playListsId)
        val users = arrayListOf<User>()
        for (e in usersToPlaylists) {
            val entity = dataBase.songDao().getUser(e.userId)
            users.add(DataBaseMappers.mapToUser(entity))
        }
        return users
    }

    fun getAllSongs(): Flow<List<Song>> {
        if (dataBase == null) {
            return emptyFlow()
        }

        return dataBase.songDao().getAllSongs().map {
            val songs = mutableListOf<Song>()
            for (entity in it) {
                songs.add(DataBaseMappers.mapToSong(entity))
            }
            songs
        }
    }
}