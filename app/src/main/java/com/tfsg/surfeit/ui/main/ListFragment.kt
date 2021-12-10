package com.tfsg.surfeit.ui.main

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.tfsg.surfeit.DatabaseManager
import com.tfsg.surfeit.R
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
        val all_the_entries = db.returnfoodtitles()
        val all_counts = db.returnfoodcount()
        val all_dates = db.returnfooddate()

        // parse this list for the things we need and populate list
        //val arr = parse_entries(all_the_entries.toTypedArray)
        // test array for now
        val arr = arrayOf("item")
        val count = arrayOf("count")
        val date = arrayOf("expiration date")
        val titles = arr + all_the_entries
        val counts = count + all_counts
        val dates = date + all_dates
        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(requireActivity(), android.R.layout.simple_list_item_1, titles)
        binding.listView.adapter = arrayAdapter
        val arrayAdapter2: ArrayAdapter<String> = ArrayAdapter(requireActivity(), android.R.layout.simple_list_item_1, counts)
        binding.listView2.adapter = arrayAdapter2
        val arrayAdapter3: ArrayAdapter<String> = ArrayAdapter(requireActivity(), android.R.layout.simple_list_item_1, dates)
        binding.listView3.adapter = arrayAdapter3
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val removeButton = view.findViewById<Button>(R.id.remove_button)

        removeButton.setOnClickListener {
            showRemoveInputBox()
        }
    }

    // un bind
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // work out what to do with all the database entries here
    fun parse_entries(entries : List<String>, counts: List<Int>) {


    }

    /**
     * Code referenced from https://www.youtube.com/watch?v=vDAO7H5w4_I
     * Input box pops up. Once input is obtained from the user, check if the product exists or not.
     * If the product exists, remove from database, else, notify user that it does not exist.
     */
    private fun showRemoveInputBox() {
        val builder = AlertDialog.Builder(getActivity())
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.remove_layout, null)
        val removeProduct = dialogLayout.findViewById<EditText>(R.id.et_remove_product)

        with(builder) {
            setTitle("Enter product name")

            // When user taps on "OK"
            setPositiveButton("OK"){dialog, which ->
                val db = DatabaseManager(requireActivity())
                val removeProductString = removeProduct.text.toString()

                // If product exists, remove and update view
                // Else, notify user the product does not exist
                if (db.exist(removeProductString)) {
                    db.remove(removeProductString)

                    // TODO Code below is copy from above from onCreateView. Make a function of this since reusing code or use adapter instead.
                    val all_the_entries = db.returnfoodtitles()
                    val all_counts = db.returnfoodcount()
                    val all_dates = db.returnfooddate()
                    val arr = arrayOf("item")
                    val count = arrayOf("count")
                    val date = arrayOf("expiration date")
                    val titles = arr + all_the_entries
                    val counts = count + all_counts
                    val dates = date + all_dates
                    val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(requireActivity(), android.R.layout.simple_list_item_1, titles)
                    binding.listView.adapter = arrayAdapter
                    val arrayAdapter2: ArrayAdapter<String> = ArrayAdapter(requireActivity(), android.R.layout.simple_list_item_1, counts)
                    binding.listView2.adapter = arrayAdapter2
                    val arrayAdapter3: ArrayAdapter<String> = ArrayAdapter(requireActivity(), android.R.layout.simple_list_item_1, dates)
                    binding.listView3.adapter = arrayAdapter3

                    Toast.makeText(getActivity(), removeProductString + " successfully removed.", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(getActivity(), removeProductString + " does not exist.", Toast.LENGTH_LONG).show()
                }
            }

            // When user taps on "Cancel"
            setNegativeButton("Cancel"){dialog, which ->
                Log.d("Main", "Negative button clicked")
                Toast.makeText(getActivity(), "Canceled", Toast.LENGTH_LONG).show()
            }

            setView(dialogLayout)
            show()
        }
    }
}