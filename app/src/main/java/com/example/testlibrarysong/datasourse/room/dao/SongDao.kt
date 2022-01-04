package com.example.testlibrarysong.datasourse.room.dao

import androidx.room.*
import com.example.testlibrarysong.datasourse.room.entities.SongEntity
import com.example.testlibrarysong.datasourse.room.relation.PlaylistsBySong
import kotlinx.coroutines.flow.Flow

@Dao
interface SongDao {

    @Query("SELECT * FROM songs")
    fun getAllSongs(): Flow<List<SongEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllSongs(songs: List<SongEntity>)

    @Transaction
    @Query("SELECT * FROM songs WHERE songId == :songId")
    suspend fun getPlaylistsBySong(songId: Int): PlaylistsBySong


}