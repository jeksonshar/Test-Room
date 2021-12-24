package com.example.testlibrarysong.business.usecases

import android.util.Log
import com.example.testlibrarysong.business.domain.PlayList
import com.example.testlibrarysong.datasourse.room.MusicDataBase
import com.example.testlibrarysong.datasourse.room.mappers.DataBaseMappers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import java.util.ArrayList

class GetPlaylistsUseCase(private val dataBase: MusicDataBase?) {

    suspend fun getPlaylistsByUser(userId: Int): List<PlayList> {
        if (dataBase == null) {
            return emptyList()
        }

        val entities = dataBase.songDao().getPlaylistsByUser(userId).playlists
        val playlists = ArrayList<PlayList>()
        for (entity in entities) {
            playlists.add(DataBaseMappers.mapToPlaylist(entity))
        }
        Log.d("TAG", "getPlaylists: $playlists")
        return playlists
    }

    suspend fun getPlaylistsBySong(playlistId: Int): List<PlayList> {
        if (dataBase == null) {
            return emptyList()
        }

        val entities = dataBase.songDao().getPlaylistsBySong(playlistId).playLists
        val playlists = ArrayList<PlayList>()
        for (entity in entities) {
            playlists.add(DataBaseMappers.mapToPlaylist(entity))
        }
        return playlists
    }
}