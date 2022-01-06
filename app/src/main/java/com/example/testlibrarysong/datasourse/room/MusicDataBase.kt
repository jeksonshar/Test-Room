package com.example.testlibrarysong.datasourse.room

import android.content.Context
import androidx.room.*
import androidx.room.migration.AutoMigrationSpec
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.testlibrarysong.datasourse.room.dao.*
import com.example.testlibrarysong.datasourse.room.entities.*
import com.example.testlibrarysong.datasourse.room.migrations.MigrationFrom1To2
import com.example.testlibrarysong.datasourse.room.migrations.MigrationFrom2To3
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

@Database(
    version = 4,
    entities = [
        UserEntity::class,
        SongEntity::class,
        PlayListEntity::class,
        UserPlaylistCrossReference::class,
        PlaylistSongCrossReference::class
    ],
    autoMigrations = [
        AutoMigration(
            from = 3,
            to = 4,
            spec = MusicDataBase.AutoMigrationFrom3To4::class
        )
    ],
    exportSchema = true
)
abstract class MusicDataBase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun songDao(): SongDao
    abstract fun playlistDao(): PlaylistDao
    abstract fun usersToPlaylistsDao(): UsersToPlaylistsDao
    abstract fun playlistsToSongsDao(): PlaylistsToSongsDao

    @DeleteColumn(tableName = "songs", columnName = "songDescription")
    class AutoMigrationFrom3To4 : AutoMigrationSpec

    companion object {
        private const val DATABASE_NAME = "Songs.db"

        @Volatile
        private var INSTANCE: MusicDataBase? = null

        fun getDatabase(context: Context): MusicDataBase {

            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDataBase(context).also {
                    INSTANCE = it
                }
            }
        }

        private fun buildDataBase(context: Context): MusicDataBase {
            return Room.databaseBuilder(
                context.applicationContext,
                MusicDataBase::class.java,
                DATABASE_NAME
            ).addCallback(
                object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        // insert the data on the IO Thread

                        val jsonFile: String = context.assets
                            .open("userPlaylistsSongsDAta.json").bufferedReader()
                            .use {
                                it.readText()
                            }
                        val response = Gson().fromJson(jsonFile, ResponseData::class.java)
                        INSTANCE?.let {
                            val userDao = it.userDao()
                            val songDao = it.songDao()
                            val playlistDao = it.playlistDao()
                            val usersToPlaylistsDao = it.usersToPlaylistsDao()
                            val playlistsToSongsDao = it.playlistsToSongsDao()
                            CoroutineScope(SupervisorJob()).launch {
                                userDao.insertAllUsers(response.users)
                                playlistDao.insertAllPlaylists(response.playlists)
                                songDao.insertAllSongs(response.songs)
                                usersToPlaylistsDao.insertUsersToPlaylists(response.usersToPlaylists)
                                playlistsToSongsDao.insertPlaylistsToSongs(response.playlistsToSongs)
                            }
                        }
                    }
                }
            )
                .addMigrations(MigrationFrom1To2)
                .addMigrations(MigrationFrom2To3)
                .build()
        }
    }
}