package com.smallmeliapp

import android.os.Bundle
import android.widget.FrameLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.smallmeliapp.databinding.ActivityMainBinding
import com.smallmeliapp.home.ui.HomeFragment
import com.smallmeliapp.home.viewmodel.HomeViewModel
import com.smallmeliapp.product.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private val homeViewModel: HomeViewModel by viewModels()
    private val productViewModel: ProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val homeLoadingFragment = findViewById<FrameLayout>(R.id.homeLoading)
        val w = window
        w.statusBarColor = ResourcesCompat.getColor(resources, R.color.meli_yellow, null)

        homeViewModel.isLoading.observe(this) {
            homeLoadingFragment?.isVisible = it
        }

        productViewModel.isLoading.observe(this) {
            homeLoadingFragment?.isVisible = it
        }
        supportFragmentManager.beginTransaction().add(R.id.homeFragmentLayout, HomeFragment())
            .commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}

