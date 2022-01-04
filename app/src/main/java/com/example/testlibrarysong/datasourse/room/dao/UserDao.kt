package com.example.testlibrarysong.datasourse.room.dao

import androidx.room.*
import com.example.testlibrarysong.datasourse.room.entities.UserEntity
import com.example.testlibrarysong.datasourse.room.relation.PlaylistsByUser
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllUsers(users: List<UserEntity>)

    @Transaction
    @Query("SELECT * FROM users WHERE userId == :userId")
    suspend fun getPlaylistsByUser(userId: Int): PlaylistsByUser

    @Query("SELECT * FROM users WHERE userId == :userId")
    suspend fun getUser(userId: Int): UserEntity
}