package com.example.testlibrarysong.datasourse.room

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.testlibrarysong.datasourse.room.entities.PlayListEntity
import com.example.testlibrarysong.datasourse.room.entities.UserEntity
import com.example.testlibrarysong.datasourse.room.entities.UserPlaylistCrossReference

data class UserWithPlaylists(
    @Embedded val user: UserEntity,
    @Relation(
        parentColumn = "userId",
        entityColumn = "playlistId",
        associateBy = Junction(UserPlaylistCrossReference::class)
    )
    val playlists: List<PlayListEntity>
)
