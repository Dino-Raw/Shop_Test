package com.example.onlineshopsatriaadhipradana.presentation.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.onlineshopsatriaadhipradana.R
import com.example.onlineshopsatriaadhipradana.app.App
import com.example.onlineshopsatriaadhipradana.databinding.FragmentSignInBinding
import com.example.onlineshopsatriaadhipradana.di.ViewModelFactory
import com.example.onlineshopsatriaadhipradana.presentation.activity.MainActivity
import com.example.onlineshopsatriaadhipradana.presentation.activity.ShopActivity
import com.example.onlineshopsatriaadhipradana.presentation.viewmodel.SignInViewModel
import javax.inject.Inject

class SignInFragment: Fragment() {
    @Inject lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: SignInViewModel
    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ((activity as MainActivity).applicationContext as App).appComponent.inject(this)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory)[SignInViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignInBinding.inflate(inflater)
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
        binding.logInText.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_sign_in_to_fragment_log_in)
        }

        binding.signInButton.setOnClickListener {
            viewModel.signIn()
        }
    }

    private fun initObservers() {
        viewModel.message.observe(viewLifecycleOwner) { message ->
            if (message.isNotBlank()) Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }


        viewModel.isSignIn.observe(viewLifecycleOwner) { isSignIn ->
            if (isSignIn) startActivity(
                Intent(requireContext(), ShopActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                }
            )
        }
    }
}