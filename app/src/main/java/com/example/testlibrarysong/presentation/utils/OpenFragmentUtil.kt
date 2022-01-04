package com.example.testlibrarysong.presentation.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.testlibrarysong.R

class OpenFragmentUtil {

    companion object {
        fun openFragment(fragmentManager: FragmentManager, fragment: Fragment) {
            fragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragmentContainer, fragment)
                .commit()
        }
    }

}