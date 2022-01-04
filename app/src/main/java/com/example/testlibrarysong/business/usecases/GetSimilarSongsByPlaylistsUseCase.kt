package com.example.testlibrarysong.business.usecases

import com.example.testlibrarysong.business.domain.models.PlayList
import com.example.testlibrarysong.business.domain.models.Song
import com.example.testlibrarysong.datasourse.room.MusicDataBase
import com.example.testlibrarysong.datasourse.room.mappers.DataBaseMappers

class GetSimilarSongsByPlaylistsUseCase(
    private val dataBase: MusicDataBase?
) {

    suspend fun getSimilarSongsByPlaylists(playlists: List<PlayList>): List<Song> {
        if (dataBase == null) {
            return emptyList()
        }

        val songsSet = mutableSetOf<Song>()
        val songList = arrayListOf<Song>()
        for (playlist in playlists) {
            val entities = dataBase.playlistDao().getSongsByPlaylist(playlist.id).songs

            for (entity in entities) {
                songsSet.add(DataBaseMappers.mapToSong(entity))
            }
        }
        songsSet.map {
            songList.add(it)
        }
        return songList
    }
}