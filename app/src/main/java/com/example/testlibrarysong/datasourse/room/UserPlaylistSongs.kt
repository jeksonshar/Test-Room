package com.example.testlibrarysong.datasourse.room

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.testlibrarysong.datasourse.room.entities.PlayListEntity
import com.example.testlibrarysong.datasourse.room.entities.PlaylistSongCrossReference
import com.example.testlibrarysong.datasourse.room.entities.SongEntity

data class UserPlaylistSongs(
    @Embedded val playlist: PlayListEntity,
    @Relation(
        parentColumn = "playlistId",
        entityColumn = "songId",
        associateBy = Junction(PlaylistSongCrossReference::class)
    )
    val songs: List<SongEntity>
)
