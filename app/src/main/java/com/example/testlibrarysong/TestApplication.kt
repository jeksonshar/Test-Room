package com.example.testlibrarysong

import android.app.Application
import com.example.testlibrarysong.datasourse.room.MusicDataBase

class TestApplication : Application() {

    var dataBase: MusicDataBase? = null

    override fun onCreate() {
        super.onCreate()

        dataBase = MusicDataBase.getDatabase(applicationContext)

    }
}