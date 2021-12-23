package com.example.testlibrarysong.datasourse.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Transaction
import com.example.testlibrarysong.business.domain.PlayList
import com.example.testlibrarysong.datasourse.room.entities.*
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.json.Json

@Dao
interface MusicDao {

    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<UserEntity>>

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

    @Transaction
    @Query("SELECT * FROM users WHERE userId == :userId")
    suspend fun getPlaylistsByUser(userId: Int): UserWithPlaylists


}