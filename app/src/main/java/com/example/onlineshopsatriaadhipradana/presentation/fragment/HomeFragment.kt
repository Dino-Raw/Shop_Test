package com.example.onlineshopsatriaadhipradana.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.onlineshopsatriaadhipradana.app.App
import com.example.onlineshopsatriaadhipradana.databinding.FragmentHomeBinding
import com.example.onlineshopsatriaadhipradana.di.ViewModelFactory
import com.example.onlineshopsatriaadhipradana.presentation.viewmodel.HomeViewModel
import javax.inject.Inject

class HomeFragment: Fragment() {
    @Inject lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().applicationContext as App).appComponent.inject(this)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory)[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        initButtons()
        initObservers()
    }

    private fun initButtons() {
        binding.homeProfileImage.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun initObservers() {
        viewModel.isDataReceived.observe(viewLifecycleOwner) {
            if (it != null && it == true) viewModel.setAdapters()
        }

        viewModel.message.observe(viewLifecycleOwner) { message ->
            if (message.isNotBlank()) Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }

        viewModel.searchWord.observe(viewLifecycleOwner) { viewModel.getSearchProductList() }
        viewModel.searchList.observe(viewLifecycleOwner) { viewModel.setSearchAdapter() }
    }


}