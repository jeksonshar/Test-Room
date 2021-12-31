package com.example.testlibrarysong.business.usecases

import com.example.testlibrarysong.business.domain.models.PlayList
import com.example.testlibrarysong.datasourse.room.MusicDataBase
import com.example.testlibrarysong.datasourse.room.mappers.DataBaseMappers
import java.util.ArrayList

class GetPlaylistsByUserUseCase(
    private val dataBase: MusicDataBase?
) {
    suspend fun getPlaylistsByUser(userId: Int): List<PlayList> {
        if (dataBase == null) {
            return emptyList()
        }

        val entities = dataBase.songDao().getPlaylistsByUser(userId).playlists
        val playlists = ArrayList<PlayList>()
        for (entity in entities) {
            playlists.add(DataBaseMappers.mapToPlaylist(entity))
        }
        return playlists
    }
}