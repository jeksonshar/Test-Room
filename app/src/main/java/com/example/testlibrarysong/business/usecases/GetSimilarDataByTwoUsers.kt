package com.example.testlibrarysong.business.usecases

import com.example.testlibrarysong.business.domain.PlayList
import com.example.testlibrarysong.business.domain.Song
import com.example.testlibrarysong.business.domain.User
import com.example.testlibrarysong.datasourse.room.MusicDataBase
import com.example.testlibrarysong.datasourse.room.mappers.DataBaseMappers
import kotlinx.coroutines.flow.*
import java.util.ArrayList

open class GetSimilarDataByTwoUsers(
    private val dataBase: MusicDataBase?
) {

    open suspend fun getPlaylistsByUser(userId: Int): List<PlayList> {
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

    fun getAllUsers(): Flow<List<User>> {
        if (dataBase == null) {
            return emptyFlow()
        }

        return dataBase.songDao().getAllUsers().map {
            val users = mutableListOf<User>()
            for (entity in it) {
                users.add(DataBaseMappers.mapToUser(entity))
            }
            users
        }
    }

    suspend fun getSongsByPlaylists(playlists: List<PlayList>): List<Song> {
        if (dataBase == null) {
            return emptyList()
        }

        val songsSet = mutableSetOf<Song>()
        val songList = arrayListOf<Song>()
        for (playlist in playlists) {
            val entities = dataBase.songDao().getSongsByPlaylist(playlist.id).songs

            for (entity in entities) {
                songsSet.add(DataBaseMappers.mapToSong(entity))
            }
        }
        songsSet.map {
            songList.add(it)
        }
        return songList
    }

    fun compareUsersPlaylist(firstList: List<PlayList>, secondList: List<PlayList>): List<PlayList> {
        val list = arrayListOf<PlayList>()
        for (element in firstList) {
            if (secondList.contains(element)) {
                list.add(element)
            }
        }
        return list
    }
}