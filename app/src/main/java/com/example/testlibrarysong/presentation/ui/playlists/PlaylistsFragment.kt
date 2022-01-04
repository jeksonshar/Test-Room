package com.example.testlibrarysong.presentation.ui.playlists

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testlibrarysong.R
import com.example.testlibrarysong.TestApplication
import com.example.testlibrarysong.business.domain.models.PlayList
import com.example.testlibrarysong.business.domain.models.Song
import com.example.testlibrarysong.business.domain.models.User
import com.example.testlibrarysong.business.domain.singletons.SongPlaylistsSingleton
import com.example.testlibrarysong.business.domain.singletons.UserPlaylistsSingleton
import com.example.testlibrarysong.business.usecases.GetPlaylistsBySongUseCase
import com.example.testlibrarysong.business.usecases.GetPlaylistsByUserUseCase
import com.example.testlibrarysong.presentation.utils.OpenFragmentUtil
import com.example.testlibrarysong.databinding.UserListFragmentBinding
import com.example.testlibrarysong.datasourse.room.MusicDataBase
import com.example.testlibrarysong.presentation.ui.songs.SongsFragment
import com.example.testlibrarysong.presentation.ui.users.UserListFragment

class PlaylistsFragment : Fragment() {

    private var _binding: UserListFragmentBinding? = null
    private val binding get() = _binding!!

    private var db: MusicDataBase? = null

    private val viewModel: PlaylistsViewModel by viewModels {
        PlaylistsViewModelFactory(
            GetPlaylistsBySongUseCase(db),
            GetPlaylistsByUserUseCase(db)
        )
    }

    private var clickListener: PlaylistClickListener? = object : PlaylistClickListener {

        override fun openSongsByPlaylist(playlist: PlayList) {
            OpenFragmentUtil.openFragment(parentFragmentManager, SongsFragment.newInstance(playlist))
        }

        override fun openUsersByPlaylist(playlist: PlayList) {
            OpenFragmentUtil.openFragment(parentFragmentManager, UserListFragment.newInstance(playlist))
        }
    }

    private val adapter by lazy {
        if (SongPlaylistsSingleton.song == null) {
            PlaylistsAdapter(clickListener)
        } else {
            PlaylistsAdapter()
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
            recyclerUsers.layoutManager = LinearLayoutManager(context)
            recyclerUsers.adapter = adapter
            val userName: String = if (SongPlaylistsSingleton.song == null) {
                getString(R.string.playlist_by) + UserPlaylistsSingleton.user?.firstName
            } else {
                getString(R.string.playlist_by) + SongPlaylistsSingleton.song?.name
            }
            val tvUsers = requireActivity().findViewById<AppCompatTextView>(R.id.tvUsers)
            tvUsers.text = userName
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getPlaylists()

        viewModel.playlists.observe(viewLifecycleOwner, {
            adapter.submitList(it)
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

        fun newInstance(user: User): PlaylistsFragment {
            UserPlaylistsSingleton.user = user
            return PlaylistsFragment()
        }

        fun newInstance(song: Song): PlaylistsFragment {
            SongPlaylistsSingleton.song = song
            return PlaylistsFragment()
        }
    }
}