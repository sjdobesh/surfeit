package com.tfsg.surfeit.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import com.tfsg.surfeit.Food
import com.tfsg.surfeit.R
import java.util.*

class ManualInputFragment : Fragment() {

    companion object {
        fun newInstance() = ManualInputFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.manual_input, container, false)
    }

    /**
     * Main view to take in user input for the product.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val name = view.findViewById<EditText>(R.id.product_name)
        val expiration = view.findViewById<DatePicker>(R.id.expiration_date)
        val category = view.findViewById<EditText>(R.id.category_type)
        val submitButton = view.findViewById<Button>(R.id.button_submit)
        val displayMessage = view.findViewById<TextView>(R.id.message_display)

        // Once the submit button is clicked, continue
        submitButton.setOnClickListener {
            // If text fields are filled, save product into database and state that it was a success
            if (validate(name, category)) {
                // Selected date
                var month = expiration.month + 1 // Since January is 0
                var day = expiration.dayOfMonth
                var year = expiration.year

                // TODO Add product to database/list
                //val food = Food(name.text.toString(), )

                displayMessage.setText("Product: " + name.text.toString() + " successfully saved!\n" +
                        "Expiration Date: " + month + "/" + day + "/" + year
                )
            }

            // TODO Implement back button
        }
    }

    /**
     * Function to validate the product name and category are not empty.
     */
    private fun validate(name: EditText, category: EditText): Boolean {
        if (name.text.toString().isEmpty()) {
            name.error = "Name should not be blank."

            return false
        }

        if (category.text.toString().isEmpty()) {
            category.error = "Category should not be blank."

            return false
        }

        return true
    }
}