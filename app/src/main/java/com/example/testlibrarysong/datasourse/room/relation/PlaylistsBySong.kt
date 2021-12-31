package com.example.testlibrarysong.datasourse.room.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.testlibrarysong.datasourse.room.entities.PlayListEntity
import com.example.testlibrarysong.datasourse.room.entities.PlaylistSongCrossReference
import com.example.testlibrarysong.datasourse.room.entities.SongEntity

data class PlaylistsBySong(
    @Embedded val song: SongEntity,
    @Relation(
        parentColumn = "songId",
        entity = PlayListEntity::class,
        entityColumn = "playlistId",
        associateBy = Junction(PlaylistSongCrossReference::class)
    )
    val playLists: List<PlayListEntity>
)
