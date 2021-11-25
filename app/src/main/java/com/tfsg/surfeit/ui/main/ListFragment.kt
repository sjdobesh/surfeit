package com.tfsg.surfeit.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.tfsg.surfeit.databinding.ListFragmentBinding

class ListFragment : Fragment() {

    private var _binding: ListFragmentBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = ListFragment()
    }

    // create view with binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ListFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        val arr = arrayOf("eggs", "milk", "bread")
        arr[0] = "egg"
        arr[1] = "milk"
        arr[2] = "bread"
        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(requireActivity(), android.R.layout.simple_list_item_1, arr)
        binding.listView.adapter = arrayAdapter
        return view
    }

    // un bind
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}