package com.tfsg.surfeit.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tfsg.surfeit.R

class CameraFragment : Fragment() {

    companion object {
        fun newInstance() = CameraFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.camera_fragment, container, false)
    }

}