package com.example.onlineshopsatriaadhipradana.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.onlineshopsatriaadhipradana.app.App
import com.example.onlineshopsatriaadhipradana.databinding.FragmentProductBinding
import com.example.onlineshopsatriaadhipradana.di.ViewModelFactory
import com.example.onlineshopsatriaadhipradana.presentation.viewmodel.ProductViewModel
import javax.inject.Inject


class ProductFragment: Fragment() {
    @Inject lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: ProductViewModel
    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().applicationContext as App).appComponent.inject(this)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory)[ProductViewModel::class.java].apply {
            arguments?.getString("id").also { arg -> if (arg != null) initProduct(arg) }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentProductBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        initObservers()
        initAdapters()
        initButtons()
    }

    private fun initButtons() {
        binding.backButton.setOnClickListener { requireActivity().onBackPressed() }
        binding.addToCartButton.setOnClickListener { showText("Proceed to checkout") }
        binding.addFavoriteButton.setOnClickListener { showText("Add to favorites") }
        binding.shareButton.setOnClickListener { showText("Share a product") }
    }

    private fun initAdapters() {
        viewModel.productThumbnailAdapter.onThumbnailClick = { imageUrl ->
            binding.productViewpager.currentItem = viewModel.product.value?.imageUrls?.indexOf(imageUrl)!!
        }

        binding.productViewpager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                viewModel.productThumbnailAdapter.updateSelectedPosition(position = position)
                binding.productThumbnailRecycler.smoothScrollToPosition(position)
            }
        })
    }

    private fun initObservers() {
        viewModel.message.observe(viewLifecycleOwner) { message ->
            if (message != "") showText(message)
        }

        viewModel.product.observe(viewLifecycleOwner) { product ->
            if (product != null && product.imageUrls.isNotEmpty()) viewModel.setAdapters()
        }
    }

    private fun showText(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }
}