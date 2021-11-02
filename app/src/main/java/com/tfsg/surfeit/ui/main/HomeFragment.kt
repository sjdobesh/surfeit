package com.tfsg.surfeit.ui.main

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import com.tfsg.surfeit.R

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //finding button elements
        val cam1 = view?.findViewById<ImageView>(R.id.cameraScanLogoNav)
        val cam2 = view?.findViewById<Button>(R.id.cameraScanButton)
        val list = view?.findViewById<ImageView>(R.id.listLogo)
        //create callbacks for onclick
        cam1?.setOnClickListener(View.OnClickListener {
            //call camera fragment as next activity
            val intent = Intent(requireContext(), CameraFragment::class.java)
            startActivity(intent)
        })
        cam2?.setOnClickListener(View.OnClickListener {
            val intent = Intent(requireContext(), CameraFragment::class.java)
            startActivity(intent)
        })
        list?.setOnClickListener(View.OnClickListener {
            val intent = Intent(requireContext(), ListFragment::class.java)
            startActivity(intent)
        })

        return inflater.inflate(R.layout.home_fragment, container, false)
    }


}