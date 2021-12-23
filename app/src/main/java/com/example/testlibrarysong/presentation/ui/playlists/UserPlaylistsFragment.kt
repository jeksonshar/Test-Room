package com.example.testlibrarysong.presentation.ui.playlists

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testlibrarysong.R
import com.example.testlibrarysong.TestApplication
import com.example.testlibrarysong.business.domain.PlayList
import com.example.testlibrarysong.business.domain.User
import com.example.testlibrarysong.business.usecases.GetPlaylistsUseCase
import com.example.testlibrarysong.databinding.UserListFragmentBinding
import com.example.testlibrarysong.datasourse.room.MusicDataBase
import com.example.testlibrarysong.presentation.ui.songs.PlaylistSongsFragment

class UserPlaylistsFragment : Fragment() {

    private var _binding: UserListFragmentBinding? = null
    private val binding get() = _binding!!

    private var db: MusicDataBase? = null

    private val viewModel: UserPlaylistsViewModel by viewModels {
        UserPlaylistsViewModelFactory(GetPlaylistsUseCase(db))
    }

    private var clickListener: PlaylistClickListener? = object : PlaylistClickListener {
        override fun openPlaylistSongs(playlist: PlayList) {
            parentFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragmentContainer, PlaylistSongsFragment.newInstance(playlist))
                .commit()
        }
    }
    private val adapter by lazy {
        clickListener?.let {
            UserPlaylistAdapter(it)
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
            val userName = UserPlaylistsSingleton.user?.firstName + getString(R.string.playlist_songs)
            tvUsers.text = userName
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getPlaylists()

        viewModel.playlists.observe(viewLifecycleOwner, {
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

        fun newInstance(user: User): UserPlaylistsFragment {
            UserPlaylistsSingleton.user = user
            return UserPlaylistsFragment()
        }
    }
}