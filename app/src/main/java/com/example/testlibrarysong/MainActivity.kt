package com.example.testlibrarysong

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testlibrarysong.presintation.ui.users.UserListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, UserListFragment())
                .commit()
        }
    }
}