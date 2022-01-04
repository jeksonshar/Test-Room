package com.example.testlibrarysong.datasourse.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testlibrarysong.datasourse.room.entities.UserPlaylistCrossReference

@Dao
interface UsersToPlaylistsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsersToPlaylists(usersToPlaylist: List<UserPlaylistCrossReference>)

    @Query("SELECT * FROM UserPlaylistCrossReference WHERE playlistId IN (:playLists)")
    suspend fun getUsersByPlaylists(playLists: List<Int>): List<UserPlaylistCrossReference>
}