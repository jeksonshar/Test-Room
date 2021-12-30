package com.example.testlibrarysong

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.testlibrarysong.presentation.ui.selectsongs.SelectSongsFragment
import com.example.testlibrarysong.presentation.ui.selectusers.SelectUsersFragment
import com.example.testlibrarysong.presentation.ui.users.UserListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val spinner: Spinner = findViewById(R.id.spinner)

        ArrayAdapter.createFromResource(
            this,
            R.array.fragments,
            android.R.layout.simple_spinner_dropdown_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (savedInstanceState == null) {
                    when (position) {
                        0 -> moveToFragment(UserListFragment.newInstance())
                        1 -> moveToFragment(SelectUsersFragment())
                        2 -> moveToFragment(SelectSongsFragment())
                    }
                } else {
                    supportFragmentManager.findFragmentByTag(CURRENT_FRAGMENT_TAG)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    private fun moveToFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment, CURRENT_FRAGMENT_TAG)
            .commit()
    }

    companion object {
        const val CURRENT_FRAGMENT_TAG = "CurrentFragment"
    }
}