package com.example.testlibrarysong.presentation.ui.songs

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
import com.example.testlibrarysong.business.usecases.GetSongsUseCase
import com.example.testlibrarysong.databinding.UserListFragmentBinding
import com.example.testlibrarysong.datasourse.room.MusicDataBase

class PlaylistSongsFragment : Fragment() {

    private var _binding: UserListFragmentBinding? = null
    private val binding get() = _binding!!

    private var db: MusicDataBase? = null

    private val viewModel: PlaylistSongsViewModel by viewModels {
        PlaylistSongsViewModelFactory(GetSongsUseCase(db))
    }

    private val adapter by lazy {
        PlaylistSongsAdapter()
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
            val songs = PlaylistSongsSingleton.playList?.name + getString(R.string.playlist_songs)
            tvUsers.text = songs
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getSongs()

        viewModel.songs.observe(this, {
            adapter.submitList(it)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDetach() {
        super.onDetach()
//        clickListener = null
    }

    companion object {
        fun newInstance(playlist: PlayList): PlaylistSongsFragment {
            PlaylistSongsSingleton.playList = playlist
            return PlaylistSongsFragment()
        }
    }
}