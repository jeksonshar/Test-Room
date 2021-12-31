package com.example.testlibrarysong.presentation.ui.selectusers

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testlibrarysong.TestApplication
import com.example.testlibrarysong.business.usecases.GetAllUsersUseCase
import com.example.testlibrarysong.business.usecases.GetPlaylistsByUserUseCase
import com.example.testlibrarysong.business.usecases.GetSimilarPlaylistsByTwoUsersUseCase
import com.example.testlibrarysong.business.usecases.GetSimilarSongsByPlaylistsUseCase
import com.example.testlibrarysong.databinding.SelectUsersFragmentBinding
import com.example.testlibrarysong.datasourse.room.MusicDataBase

class SelectUsersFragment : Fragment() {

    private var _binding: SelectUsersFragmentBinding? = null
    private val binding get() = _binding!!

    private var db: MusicDataBase? = null

    private val viewModel: SelectUsersViewModel by viewModels {
        SelectUsersViewModelFactory(
            GetSimilarSongsByPlaylistsUseCase(db),
            GetAllUsersUseCase(db),
            GetPlaylistsByUserUseCase(db),
            GetSimilarPlaylistsByTwoUsersUseCase()
        )
    }

    private val adapterPlaylists by lazy {
        SelectUsersRecyclerPlaylistsAdapter()
    }

    private val adapterSongs by lazy {
        SelectUsersRecyclerSongsAdapter()
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
        _binding = SelectUsersFragmentBinding.inflate(inflater, container, false)
        binding.apply {
            recyclerSelectUsers.layoutManager = LinearLayoutManager(context)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.usersData.observe(viewLifecycleOwner, {
//            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, it)
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            val spinnerAdapter = SelectUsersSpinnerAdapter(requireContext(), it)
            binding.spinnerFirst.adapter = spinnerAdapter
            binding.spinnerSecond.adapter = spinnerAdapter
        })

        viewModel.songsByPlaylists.observe(viewLifecycleOwner, {
            binding.recyclerSelectUsers.adapter = adapterSongs
            adapterSongs.submitList(it)
        })


        binding.apply {
            spinnerFirst.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    viewModel.getFirstUserPlaylists(position)
                    adapterPlaylists.submitList(null)
                    adapterSongs.submitList(null)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }

            spinnerSecond.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    viewModel.getSecondUserPlaylists(position)
                    adapterPlaylists.submitList(null)
                    adapterSongs.submitList(null)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
            btnShowSimilarPlaylists.setOnClickListener {
                val similarPlaylists = viewModel.compareUsersPlayList()
                recyclerSelectUsers.adapter = adapterPlaylists
                adapterPlaylists.submitList(similarPlaylists)
            }
            btnShowSimilarSongs.setOnClickListener {
                viewModel.showAllSongsByPlaylists()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
