package com.example.testlibrarysong.datasourse.room

import android.content.Context
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.testlibrarysong.datasourse.room.entities.*
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
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
abstract class SongDataBase : RoomDatabase() {
    abstract fun songDao(): SongDao

    companion object {
        private const val DATABASE_NAME = "Songs.db"

        fun create(context: Context): SongDataBase {

            return Room.databaseBuilder(
                context,
                SongDataBase::class.java,
                DATABASE_NAME
            ).build()
//                .addCallback(
//                    object : Callback() {
//                        override fun onCreate(db: SupportSQLiteDatabase) {
//                            super.onCreate(db)
//                            // insert the data on the IO Thread
//
//                            val jsonFile: String = context.assets
//                                .open("userPlaylistsSongsDAta.json").bufferedReader()
//                                .use {
//                                    it.readText()
//                                }
//                            val response = Gson().fromJson(jsonFile, ResponseData::class.java)
//                            val dao = create(context).songDao()
//                            CoroutineScope(Dispatchers.IO).launch {
//                                dao.insertAllUsers(response.users)
//                                dao.insertAllPlaylists(response.playlists)
//                                dao.insertAllSongs(response.songs)
//                                dao.insertUsersToPlaylists(response.usersToPlaylists)
//                                dao.insertPlaylistsToSongs(response.playlistsToSongs)
//                            }
//                        }
//                    }
//                )
//                .build()
        }
    }
}