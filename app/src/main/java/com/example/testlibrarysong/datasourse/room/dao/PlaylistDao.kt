package com.example.testlibrarysong.datasourse.room.dao

import androidx.room.*
import com.example.testlibrarysong.datasourse.room.entities.PlayListEntity
import com.example.testlibrarysong.datasourse.room.relation.SongsByPlaylist
import com.example.testlibrarysong.datasourse.room.relation.UsersByPlaylist
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaylistDao {

    @Transaction
    @Query("SELECT * FROM play_lists WHERE playlistId == :plId")
    fun getUsersByPlaylist(plId: Int): Flow<UsersByPlaylist>

    @Transaction
    @Query("SELECT * FROM play_lists WHERE playlistId == :playlistId")
    suspend fun getSongsByPlaylist(playlistId: Int): SongsByPlaylist

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPlaylists(playLists: List<PlayListEntity>)
}