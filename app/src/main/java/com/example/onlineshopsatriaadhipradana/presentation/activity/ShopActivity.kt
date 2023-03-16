package com.example.onlineshopsatriaadhipradana.presentation.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.onlineshopsatriaadhipradana.R
import com.example.onlineshopsatriaadhipradana.databinding.ActivityShopBinding

class ShopActivity : AppCompatActivity() {
    private var _binding: ActivityShopBinding? = null
    private val binding get() = _binding!!
    private var backPressed = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityShopBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        setContentView(binding.root)

        binding.bottomNavigation.setupWithNavController(
            findNavController(R.id.shop_navigation_fragment)
        )

        //initDestinationParameters()
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    fun initDestinationParameters() {
        findNavController(R.id.shop_navigation_fragment).addOnDestinationChangedListener { _, destination, arguments ->
            if (destination.id == R.id.fragment_product)
                binding.root.background = resources.getDrawable(R.color.product_background)
            else
                binding.root.background = resources.getDrawable(R.color.app_background)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (findNavController(R.id.shop_navigation_fragment).navigateUp().not()) {
            if (backPressed) {
                backPressed = false
                finishAffinity()
            } else {
                backPressed = true

                Handler().postDelayed(Runnable {
                    backPressed = false
                }, 3000)

                Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show()
            }
        }
    }
}