package com.ramonapp.android.bestpracticetask.presentation.albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.snackbar.Snackbar
import com.ramonapp.android.bestpracticetask.databinding.FragmentAlbumsBinding
import com.ramonapp.android.bestpracticetask.extension.gone
import com.ramonapp.android.bestpracticetask.extension.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AlbumsFragment : Fragment() {

    private val viewModel by viewModels<AlbumsViewModel>()
    private var _binding: FragmentAlbumsBinding? = null
    private val binding get() = _binding!!
    private val adapter = AlbumAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlbumsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListAdapter()
        setupObservers()
        viewModel.getAlbumsData()
    }

    private fun setupListAdapter() {
        binding.albumRecycler.adapter = adapter
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collectLatest { uiState ->
                    adapter.submitList(uiState.albums)

                    if (uiState.isLoading) {
                        binding.albumProgress.show()
                    } else {
                        binding.albumProgress.gone()
                    }

                    if (uiState.errorMessage.isNotBlank()) {
                        Snackbar.make(binding.root, uiState.errorMessage, Snackbar.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}