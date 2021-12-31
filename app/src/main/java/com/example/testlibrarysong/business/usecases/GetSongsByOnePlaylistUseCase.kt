package com.example.testlibrarysong.business.usecases

import com.example.testlibrarysong.business.domain.models.Song
import com.example.testlibrarysong.datasourse.room.MusicDataBase
import com.example.testlibrarysong.datasourse.room.mappers.DataBaseMappers

class GetSongsByOnePlaylistUseCase(
    private val dataBase: MusicDataBase?
) {

    suspend fun getSongsByOnePlaylist(playlistId: Int): List<Song> {
        if (dataBase == null) {
            return emptyList()
        }

        val entities = dataBase.songDao().getSongsByPlaylist(playlistId).songs
        val songs = ArrayList<Song>()
        for (entity in entities) {
            songs.add(DataBaseMappers.mapToSong(entity))
        }
        return songs
    }
}