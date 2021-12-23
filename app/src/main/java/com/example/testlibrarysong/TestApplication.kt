package com.example.testlibrarysong

import android.app.Application
import com.example.testlibrarysong.datasourse.DataStore
import com.example.testlibrarysong.datasourse.room.SongDataBase
import com.example.testlibrarysong.datasourse.room.entities.ResponseData
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class TestApplication : Application() {

    val dataStore: DataStore by lazy { DataStore(applicationContext) }
    val dataBase: SongDataBase by lazy { SongDataBase.create(applicationContext) }

    override fun onCreate() {
        super.onCreate()


        CoroutineScope(Dispatchers.IO).launch {
            dataStore.getFirstLaunch().collectLatest { isFirstLaunch ->
                if (!isFirstLaunch) {
                    val jsonFile: String = applicationContext.assets
                        .open("userPlaylistsSongsDAta.json").bufferedReader()
                        .use {
                            it.readText()
                        }
                    val response = Gson().fromJson(jsonFile, ResponseData::class.java)
                    dataBase.songDao().insertAllUsers(response.users)
                    dataBase.songDao().insertAllPlaylists(response.playlists)
                    dataBase.songDao().insertAllSongs(response.songs)
                    dataBase.songDao().insertUsersToPlaylists(response.usersToPlaylists)
                    dataBase.songDao().insertPlaylistsToSongs(response.playlistsToSongs)
                    dataStore.setFirstLaunch()
                }
            }
        }

    }
}