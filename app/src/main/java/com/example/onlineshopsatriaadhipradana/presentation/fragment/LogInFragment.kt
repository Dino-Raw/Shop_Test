package com.example.onlineshopsatriaadhipradana.presentation.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.onlineshopsatriaadhipradana.app.App
import com.example.onlineshopsatriaadhipradana.databinding.FragmentLogInBinding
import com.example.onlineshopsatriaadhipradana.di.ViewModelFactory
import com.example.onlineshopsatriaadhipradana.presentation.activity.ShopActivity
import com.example.onlineshopsatriaadhipradana.presentation.viewmodel.LogInViewModel
import javax.inject.Inject

class LogInFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: LogInViewModel
    private var _binding: FragmentLogInBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().applicationContext as App).appComponent.inject(this)
        viewModel =
            ViewModelProvider(requireActivity(), viewModelFactory)[LogInViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentLogInBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        initObservers()
        initButtons()
    }

    private fun initButtons() {
        binding.logInButton.setOnClickListener {
            viewModel.logIn()
        }
    }

    private fun initObservers() {
        viewModel.message.observe(viewLifecycleOwner) { message ->
            if (message.isNotBlank()) Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }

        viewModel.isLogin.observe(viewLifecycleOwner) { isLogin ->
            if (isLogin) startActivity(
                Intent(requireContext(), ShopActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                }
            )
        }
    }
}