package com.example.testlibrarysong

import android.app.Application
import com.example.testlibrarysong.datasourse.DataStore
import com.example.testlibrarysong.datasourse.room.MusicDataBase
import com.example.testlibrarysong.datasourse.room.entities.ResponseData
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class TestApplication : Application() {

    var dataBase: MusicDataBase? = null

    override fun onCreate() {
        super.onCreate()

        dataBase = MusicDataBase.create(applicationContext)

    }
}