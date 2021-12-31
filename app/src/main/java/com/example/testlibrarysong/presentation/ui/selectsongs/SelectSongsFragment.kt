package com.example.testlibrarysong.presentation.ui.selectsongs

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testlibrarysong.R
import com.example.testlibrarysong.TestApplication
import com.example.testlibrarysong.business.usecases.GetAllSongsUseCase
import com.example.testlibrarysong.business.usecases.GetPlaylistsBySongUseCase
import com.example.testlibrarysong.business.usecases.GetUsersBySongUseCase
import com.example.testlibrarysong.databinding.SelectUsersFragmentBinding
import com.example.testlibrarysong.datasourse.room.MusicDataBase
import com.example.testlibrarysong.presentation.ui.selectusers.SelectUsersRecyclerPlaylistsAdapter

//class SelectSongsFragment : BaseSelectFragment<SelectUsersFragmentBinding>(SelectUsersFragmentBinding::inflate) {
class SelectSongsFragment : Fragment() {

    private var _binding: SelectUsersFragmentBinding? = null
    private val binding get() = _binding!!

    private var db: MusicDataBase? = null

    val viewModel: SelectSongViewModel by viewModels {
        SelectSongViewModelFactory(
            GetUsersBySongUseCase(db),
            GetAllSongsUseCase(db),
            GetPlaylistsBySongUseCase(db)
        )
    }

    private val adapterPlaylists by lazy {
        SelectUsersRecyclerPlaylistsAdapter()
    }

    private val adapterUsers by lazy {
        SelectSongsRecyclerUsersAdapter()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        db = (requireActivity().applicationContext as TestApplication).dataBase
    }

    // с базовым фрагментом, пока не работает!
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
//        binding.recyclerSelectUsers.layoutManager = LinearLayoutManager(context)
//        binding.spinnerSecond.visibility = View.GONE
//        binding.tvSelectUsers.setText(R.string.select_songs)
//        binding.btnShowSimilarSongs.setText(R.string.similar_users)
//        return super.onCreateView(inflater, container, savedInstanceState)
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SelectUsersFragmentBinding.inflate(inflater, container, false)
        binding.apply {
            recyclerSelectUsers.layoutManager = LinearLayoutManager(context)
            spinnerSecond.visibility = View.GONE
            tvSelectUsers.setText(R.string.select_songs)
            btnShowSimilarSongs.setText(R.string.similar_users)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.songData.observe(viewLifecycleOwner, {
            val spinnerAdapter = SelectSongsSpinnerAdapter(requireContext(), it)
            binding.spinnerFirst.adapter = spinnerAdapter
        })

        viewModel.usersBySong.observe(viewLifecycleOwner, {
            binding.recyclerSelectUsers.adapter = adapterUsers
            adapterUsers.submitList(it)
        })

        viewModel.playlistsBySong.observe(viewLifecycleOwner, {
            binding.recyclerSelectUsers.adapter = adapterPlaylists
            adapterPlaylists.submitList(it)
        })

        binding.apply {
            spinnerFirst.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                    viewModel.getPlaylistsBySong(viewModel.songData.value?.get(position)?.id ?: 1) // список появляется при старте фрагмента ((
                    viewModel.selectSongId(position)
                    adapterPlaylists.submitList(null)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }

            btnShowSimilarPlaylists.setOnClickListener {
                viewModel.getPlaylistsBySong()
            }

            btnShowSimilarSongs.setOnClickListener {
                viewModel.getUsersBySong()
            }
        }
    }
}