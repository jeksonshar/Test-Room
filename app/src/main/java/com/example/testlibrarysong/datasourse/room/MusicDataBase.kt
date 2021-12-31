package com.example.testlibrarysong.datasourse.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.testlibrarysong.datasourse.room.dao.MusicDao
import com.example.testlibrarysong.datasourse.room.entities.*
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

@Database(
    entities = [
        UserEntity::class,
        SongEntity::class,
        PlayListEntity::class,
        UserPlaylistCrossReference::class,
        PlaylistSongCrossReference::class
    ], version = 1
)
abstract class MusicDataBase : RoomDatabase() {
    abstract fun songDao(): MusicDao

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
                            val dao = it.songDao()
                            CoroutineScope(SupervisorJob()).launch {
                                dao.insertAllUsers(response.users)
                                dao.insertAllPlaylists(response.playlists)
                                dao.insertAllSongs(response.songs)
                                dao.insertUsersToPlaylists(response.usersToPlaylists)
                                dao.insertPlaylistsToSongs(response.playlistsToSongs)
                            }
                        }
                    }
                }
            )
                .build()
        }
    }
}