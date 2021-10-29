package com.tfsg.surfeit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tfsg.surfeit.ui.main.CameraFragment
import com.tfsg.surfeit.ui.main.ConfirmScanFragment
import com.tfsg.surfeit.ui.main.HomeFragment
import com.tfsg.surfeit.ui.main.ListFragment
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        // initialize our fragments
        val home = HomeFragment()
        val list = ListFragment()
        val cam  = CameraFragment()
        // val scan = ConfirmScanFragment()

        setFrag(home);

        // bottom nav controls
        bottom_nav.setOnNavigationItemSelectedListener {
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
    }

    // set a fragment to be displayed
    private fun setFrag(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment);
            commit();
        }
}
