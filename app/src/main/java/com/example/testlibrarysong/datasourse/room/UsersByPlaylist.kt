package com.example.testlibrarysong.datasourse.room

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.testlibrarysong.datasourse.room.entities.PlayListEntity
import com.example.testlibrarysong.datasourse.room.entities.UserEntity
import com.example.testlibrarysong.datasourse.room.entities.UserPlaylistCrossReference

data class UsersByPlaylist(
    @Embedded val playList: PlayListEntity,
    @Relation(
        parentColumn = "playlistId",
        entityColumn = "userId",
        associateBy = Junction(UserPlaylistCrossReference::class)
    )
    val users: List<UserEntity>
)
