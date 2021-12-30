package com.example.testlibrarysong.business.usecases

import com.example.testlibrarysong.business.domain.PlayList
import com.example.testlibrarysong.datasourse.room.MusicDataBase
import com.example.testlibrarysong.datasourse.room.mappers.DataBaseMappers
import java.util.*

open class GetPlaylistsUseCase(private val dataBase: MusicDataBase?) : GetSimilarDataByTwoUsers(dataBase) {

//    suspend fun getPlaylistsByUser(userId: Int): List<PlayList> {
//        if (dataBase == null) {
//            return emptyList()
//        }
//
//        val entities = dataBase.songDao().getPlaylistsByUser(userId).playlists
//        val playlists = ArrayList<PlayList>()
//        for (entity in entities) {
//            playlists.add(DataBaseMappers.mapToPlaylist(entity))
//        }
//        Log.d("TAG", "getPlaylists: $playlists")
//        return playlists
//    }

    open suspend fun getPlaylistsBySong(songId: Int): List<PlayList> {
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