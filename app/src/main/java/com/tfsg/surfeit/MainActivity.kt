package com.tfsg.surfeit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tfsg.surfeit.databinding.MainActivityBinding
import com.tfsg.surfeit.ui.main.CameraFragment
// import com.tfsg.surfeit.ui.main.ConfirmScanFragment
import com.tfsg.surfeit.ui.main.HomeFragment
import com.tfsg.surfeit.ui.main.ListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // initialize our fragments
        val home = HomeFragment()
        val list = ListFragment()
        val cam  = CameraFragment()
        // val scan = ConfirmScanFragment()

        setFrag(home)

        // bottom nav controls
        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.fridge -> {
                    setFrag(list)
                    true
                }
                R.id.home   -> {
                    setFrag(home)
                    true
                }
                R.id.camera -> {
                    setFrag(cam)
                    true
                }
                else-> false
            }
        }
        // set initial page to home page
        binding.bottomNav.selectedItemId = R.id.home
    }

    // set a fragment to be displayed
    private fun setFrag(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }
}
