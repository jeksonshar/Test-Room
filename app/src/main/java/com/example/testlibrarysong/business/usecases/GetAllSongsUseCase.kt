package com.example.testlibrarysong.business.usecases

import com.example.testlibrarysong.business.domain.models.Song
import com.example.testlibrarysong.datasourse.room.MusicDataBase
import com.example.testlibrarysong.datasourse.room.mappers.DataBaseMappers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map

class GetAllSongsUseCase(
    private val dataBase: MusicDataBase?
) {
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