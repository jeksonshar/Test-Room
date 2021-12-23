package com.example.testlibrarysong.datasourse.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Transaction
import com.example.testlibrarysong.business.domain.PlayList
import com.example.testlibrarysong.datasourse.room.entities.*
import kotlinx.serialization.json.Json

@Dao
interface SongDao {

    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<UserEntity>

    @Query("SELECT * FROM play_lists")
    suspend fun getAllPlaylists(): List<PlayListEntity>

    @Insert(onConflict = REPLACE)
    suspend fun insertAllUsers(users: List<UserEntity>)

    @Insert(onConflict = REPLACE)
    suspend fun insertAllPlaylists(playLists: List<PlayListEntity>)

    @Insert(onConflict = REPLACE)
    suspend fun insertAllSongs(songs: List<SongEntity>)

    @Insert(onConflict = REPLACE)
    suspend fun insertUsersToPlaylists(usersToPlaylist: List<UserPlaylistCrossReference>)

    @Insert(onConflict = REPLACE)
    suspend fun insertPlaylistsToSongs(playlistsToSongs: List<PlaylistSongCrossReference>)

//    @Transaction
//    @Query("SELECT * FROM UserPlaylistCrossReference WHERE userId == :userId")
//    fun getPlaylistsByUser(userId: Int): UserWithPlaylists


}