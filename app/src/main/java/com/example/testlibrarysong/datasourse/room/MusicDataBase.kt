package com.example.testlibrarysong.datasourse.room

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.testlibrarysong.datasourse.room.dao.*
import com.example.testlibrarysong.datasourse.room.entities.*
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.random.Random

@Database(
    entities = [
        UserEntity::class,
        SongEntity::class,
        PlayListEntity::class,
        UserPlaylistCrossReference::class,
        PlaylistSongCrossReference::class
    ],
    version = 2
)
abstract class MusicDataBase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun songDao(): SongDao
    abstract fun playlistDao(): PlaylistDao
    abstract fun usersToPlaylistsDao(): UsersToPlaylistsDao
    abstract fun playlistsToSongsDao(): PlaylistsToSongsDao

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

        fun randomValue(): Float {
            return Random.nextDouble(0.0, 10.0).toFloat()
        }

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {

                database.execSQL("ALTER TABLE play_lists ADD COLUMN playlistRating REAL NOT NULL DEFAULT 0.0")
                val cursor = database.query("SELECT * FROM play_lists")

                if (cursor.moveToFirst()) {
                    do {
                        val values = ContentValues()
                        values.put("playlistRating", randomValue())

                        val columnIndex = cursor.getColumnIndex("playlistId")
                        database.update(
                            "play_lists",
                            SQLiteDatabase.CONFLICT_REPLACE,
                            values,
                            "playlistId = ?",
                            arrayOf(cursor.getInt(columnIndex))
                        )
                    } while (cursor.moveToNext())
                }
                cursor.close()
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
                .addMigrations(MIGRATION_1_2)
                .build()
        }
    }
}