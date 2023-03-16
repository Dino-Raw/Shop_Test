package com.example.onlineshopsatriaadhipradana.presentation.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts.GetContent
import androidx.fragment.app.Fragment
import com.example.onlineshopsatriaadhipradana.databinding.FragmentProfileBinding
import com.example.onlineshopsatriaadhipradana.presentation.activity.MainActivity
import com.example.onlineshopsatriaadhipradana.util.transform
import com.squareup.picasso.Picasso


class ProfileFragment: Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private var gallery =
        registerForActivityResult(GetContent()) { uri ->
            if (uri != null)
                Picasso.get()
                    .load(uri)
                    .transform(transform)
                    .into(binding.profileImage)
            else
                Toast
                    .makeText(requireContext(), "Failed to load image", Toast.LENGTH_SHORT)
                    .show()
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        initButtons()
    }

    private fun initButtons() {
        binding.logOutLayout.setOnClickListener {
            startActivity(
                Intent(requireContext(),MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                }
            )
        }

        binding.changePhotoText.setOnClickListener { gallery.launch("image/*") }
    }
}