package com.example.testlibrarysong.datasourse.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.testlibrarysong.datasourse.room.entities.PlaylistSongCrossReference

@Dao
interface PlaylistsToSongsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlaylistsToSongs(playlistsToSongs: List<PlaylistSongCrossReference>)
}