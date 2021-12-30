package com.example.testlibrarysong.datasourse.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Transaction
import com.example.testlibrarysong.business.domain.Song
import com.example.testlibrarysong.business.domain.User
import com.example.testlibrarysong.datasourse.room.entities.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MusicDao {

    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<UserEntity>>

    @Insert(onConflict = REPLACE)
    suspend fun insertAllUsers(users: List<UserEntity>)

    @Query("SELECT * FROM songs")
    fun getAllSongs():Flow<List<SongEntity>>

    @Insert(onConflict = REPLACE)
    suspend fun insertAllSongs(songs: List<SongEntity>)


    @Transaction
    @Query("SELECT * FROM play_lists WHERE playlistId == :plId")
    fun getUsersByPlaylist(plId: Int): Flow<UsersByPlaylist>

    @Transaction
    @Query("SELECT * FROM songs WHERE songId == :songId")
    suspend fun getPlaylistsBySong(songId: Int): PlaylistsBySong

    @Transaction
    @Query("SELECT * FROM users WHERE userId == :userId")
    suspend fun getPlaylistsByUser(userId: Int): PlaylistsByUser

    @Transaction
    @Query("SELECT * FROM play_lists WHERE playlistId == :playlistId")
    suspend fun getSongsByPlaylist(playlistId: Int): SongsByPlaylist

    @Insert(onConflict = REPLACE)
    suspend fun insertAllPlaylists(playLists: List<PlayListEntity>)

    @Insert(onConflict = REPLACE)
    suspend fun insertPlaylistsToSongs(playlistsToSongs: List<PlaylistSongCrossReference>)

    @Insert(onConflict = REPLACE)
    suspend fun insertUsersToPlaylists(usersToPlaylist: List<UserPlaylistCrossReference>)



    @Query("SELECT * FROM UserPlaylistCrossReference WHERE playlistId IN (:playLists)")
    suspend fun getUsersByPlaylists(playLists: List<Int>): List<UserPlaylistCrossReference>

    @Query("SELECT * FROM users WHERE userId == :userId")
    suspend fun getUser(userId: Int): UserEntity

}