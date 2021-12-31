package com.example.testlibrarysong.business.usecases

import com.example.testlibrarysong.business.domain.models.PlayList
import com.example.testlibrarysong.datasourse.room.MusicDataBase
import com.example.testlibrarysong.datasourse.room.mappers.DataBaseMappers
import java.util.*

/*open */class GetPlaylistsBySongUseCase(
    private val dataBase: MusicDataBase?
    )/* : GetSimilarDataByTwoUsersUseCase(dataBase)*/ {

    /*open */suspend fun getPlaylistsBySong(songId: Int): List<PlayList> {
        if (dataBase == null) {
            return emptyList()
        }

        val entities = dataBase.songDao().getPlaylistsBySong(songId).playLists
        val playlists = ArrayList<PlayList>()
        for (entity in entities) {
            playlists.add(DataBaseMappers.mapToPlaylist(entity))
        }
        return playlists
    }
}