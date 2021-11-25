package com.tfsg.surfeit.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.tfsg.surfeit.DatabaseManager
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
        // normally we will read from the database here
        val db = DatabaseManager(requireActivity())
        val all_the_entries = db.selectAll()
        // parse this list for the things we need and populate list
        // val arr = parse_entries(all_the_entries.toTypedArray)
        // test array for now
        val arr = arrayOf("eggs", "milk", "bread")
        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(requireActivity(), android.R.layout.simple_list_item_1, arr)
        binding.listView.adapter = arrayAdapter
        return view
    }

    // un bind
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // work out what to do with all the database entries here
    fun parse_entries(entries : List<String>) {

    }
}