package com.example.testlibrarysong.business.usecases

import com.example.testlibrarysong.business.domain.PlayList
import com.example.testlibrarysong.business.domain.User
import com.example.testlibrarysong.datasourse.room.SongDataBase
import com.example.testlibrarysong.datasourse.room.mappers.DataBaseMappers
import java.util.ArrayList

class GetPlaylistsUseCase(private val dataBase: SongDataBase) {
    suspend fun getPlaylists(): List<PlayList> {
        val entities = dataBase.songDao().getAllPlaylists()
        val playlists = ArrayList<PlayList>()
        for (entity in entities) {
            playlists.add(DataBaseMappers.mapToPlaylist(entity))
        }
        return playlists
    }
}