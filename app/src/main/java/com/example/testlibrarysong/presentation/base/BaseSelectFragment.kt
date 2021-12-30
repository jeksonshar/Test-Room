package com.example.testlibrarysong.presentation.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.testlibrarysong.TestApplication
import com.example.testlibrarysong.databinding.SelectUsersFragmentBinding
import com.example.testlibrarysong.datasourse.room.MusicDataBase
import java.lang.reflect.ParameterizedType

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T
// не получается завести от него фрагменты, падает на 22 стр.
abstract class BaseSelectFragment<VB: ViewBinding>(
    private val inflate: Inflate<VB>
) : Fragment() {

    private var _binding: VB? = null
    val binding get() = _binding!!

    var db: MusicDataBase? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        db = (requireActivity().applicationContext as TestApplication).dataBase
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        _binding = SelectUsersFragmentBinding.inflate(inflater, container, false)
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}