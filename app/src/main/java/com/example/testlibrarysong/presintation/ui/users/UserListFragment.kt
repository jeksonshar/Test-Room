package com.example.testlibrarysong.presintation.ui.users

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testlibrarysong.TestApplication
import com.example.testlibrarysong.business.usecases.GetUsersUseCase
import com.example.testlibrarysong.databinding.UserListFragmentBinding
import com.example.testlibrarysong.datasourse.DataStore
import com.example.testlibrarysong.datasourse.room.SongDataBase
import com.example.testlibrarysong.datasourse.room.entities.ResponseData
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

class UserListFragment : Fragment() {

    private var _binding: UserListFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var dataBase: SongDataBase
    private lateinit var dataStore: DataStore

//    private lateinit var app: TestApplication

    private val viewModel: UserListViewModel by viewModels {
        UserListViewModelFactory(
            GetUsersUseCase(dataBase),
            this
        )
    }

    private val adapter by lazy {
        UserListAdapter()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dataBase = SongDataBase.create(context)
        dataStore = DataStore(context)
//        app = activity?.application as TestApplication
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val jsonFile: String = requireContext().assets
            .open("userPlaylistsSongsDAta.json").bufferedReader()
            .use {
                it.readText()
            }

        val response = Gson().fromJson(jsonFile, ResponseData::class.java)
        val dao = dataBase.songDao()

        CoroutineScope(Dispatchers.IO).launch {
            dataStore.getFirstLaunch().collectLatest { isFirstLaunch ->
                if (!isFirstLaunch) {
                    dao.insertAllUsers(response.users)
                    dao.insertAllPlaylists(response.playlists)
                    dao.insertAllSongs(response.songs)
                    dao.insertUsersToPlaylists(response.usersToPlaylists)
                    dao.insertPlaylistsToSongs(response.playlistsToSongs)
                    dataStore.setFirstLaunch()
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = UserListFragmentBinding.inflate(inflater, container, false)
        binding.apply {
            val layoutManager = LinearLayoutManager(context)
            recyclerUsers.layoutManager = layoutManager
//            recyclerUsers.addItemDecoration(DividerItemDecoration(context, layoutManager.orientation))
            recyclerUsers.adapter = adapter
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUsers()

        viewModel.users.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })
    }


}