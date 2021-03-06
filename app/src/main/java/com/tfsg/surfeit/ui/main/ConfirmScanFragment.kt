package com.tfsg.surfeit.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tfsg.surfeit.R

class ConfirmScanFragment : Fragment() {

    companion object {
        fun newInstance() = ConfirmScanFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.confirm_fragment, container, false)
    }

}