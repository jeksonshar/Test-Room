package com.example.testlibrarysong.datasourse.room.mappers

import com.example.testlibrarysong.business.domain.PlayList
import com.example.testlibrarysong.business.domain.Song
import com.example.testlibrarysong.business.domain.User
import com.example.testlibrarysong.datasourse.room.entities.PlayListEntity
import com.example.testlibrarysong.datasourse.room.entities.SongEntity
import com.example.testlibrarysong.datasourse.room.entities.UserEntity

object DataBaseMappers {

    fun mapToUser(entity: UserEntity): User {
        return User(
            id = entity.userId,
            firstName = entity.userFirstName,
            lastName = entity.userLastName,
            email = entity.userEmail
        )
    }

    fun mapToPlaylist(entity: PlayListEntity): PlayList {
        return PlayList(
            id = entity.playlistId,
            name = entity.playlistName,
            description = entity.playlistDescription
        )
    }

    fun mapToSong(entity: SongEntity): Song {
        return Song(
            id = entity.songId,
            name = entity.songName,
            singerName = entity.singerFirstName,
            singerLastName = entity.singerLastName,
            description = entity.songDescription
        )
    }
}