package com.tfsg.surfeit.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import com.tfsg.surfeit.R
import com.tfsg.surfeit.databinding.ListFragmentBinding

class ListFragment : Fragment() {

    private lateinit var listview : ListView
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
        return view
    }

    // un bind
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated() {
        val arrayAdapter: ArrayAdapter<String>
        var test_arr = arrayOf("eggs", "milk", "bread")
        test_arr[0] = "egg"
        test_arr[1] = "milk"
        test_arr[1] = "bread"
        val lview =  binding.listView
        arrayAdapter = ArrayAdapter(requireActivity(), android.R.layout.simple_list_item_1)
        binding.listView.adapter = arrayAdapter
    }
}