package com.example.testlibrarysong.presentation.ui.users

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testlibrarysong.MainActivity
import com.example.testlibrarysong.R
import com.example.testlibrarysong.TestApplication
import com.example.testlibrarysong.business.domain.PlayList
import com.example.testlibrarysong.business.domain.User
import com.example.testlibrarysong.business.usecases.GetUsersUseCase
import com.example.testlibrarysong.databinding.UserListFragmentBinding
import com.example.testlibrarysong.datasourse.room.MusicDataBase
import com.example.testlibrarysong.presentation.ui.playlists.UserPlaylistsFragment
import com.example.testlibrarysong.presentation.ui.songs.PlaylistSongsSingleton

class UserListFragment : Fragment() {

    private var _binding: UserListFragmentBinding? = null
    private val binding get() = _binding!!

    var db: MusicDataBase? = null

    private val viewModel: UserListViewModel by viewModels {
        UserListViewModelFactory(
            GetUsersUseCase(db),
            this
        )
    }

    private var clickListener: UserClickListener? = object : UserClickListener {
        override fun openUsersPlaylists(user: User) {
            parentFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragmentContainer, UserPlaylistsFragment.newInstance(user))
                .commit()
        }
    }

    private val adapter by lazy {
        clickListener?.let {
            UserListAdapter(it)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        db = (requireActivity().applicationContext as TestApplication).dataBase
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
            recyclerUsers.adapter = adapter
            val tvUsers = requireActivity().findViewById<AppCompatTextView>(R.id.tvUsers)
            if (PlaylistSongsSingleton.playList != null) {
                val tmp = getString(R.string.users_list) + " by " + PlaylistSongsSingleton.playList!!.name
                tvUsers.text = tmp
            } else {
                tvUsers.setText(R.string.users_list)
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUsers()

        viewModel.users.observe(viewLifecycleOwner, {
            adapter?.submitList(it)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDetach() {
        super.onDetach()
        clickListener = null
    }

    companion object {
        fun newInstance(playList: PlayList? = null): UserListFragment {
            return if (playList == null) {
                UserListFragment()
            } else {
                PlaylistSongsSingleton.playList = playList
                UserListFragment()
            }
        }
    }


}