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
import com.tfsg.surfeit.DatabaseManager
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
        val name = view.findViewById<EditText>(R.id.product_name)
        val amount = view.findViewById<EditText>(R.id.product_amount)
        val purchase = view.findViewById<DatePicker>(R.id.purchase_date)
        val expiration = view.findViewById<DatePicker>(R.id.expiration_date)
        val category = view.findViewById<EditText>(R.id.category_type)
        val submitButton = view.findViewById<Button>(R.id.button_submit)
        val displayMessage = view.findViewById<TextView>(R.id.message_display)

        // Once the submit button is clicked, continue
        submitButton.setOnClickListener {
            // If text fields are filled, save product into database and state that it was a success
            if (validate(name, amount, category)) {
                // Selected purchase date
                var pYear = purchase.year
                var pMonth = purchase.month + 1 // Since January is 0
                var pDay = purchase.dayOfMonth
                val pDate = convertDate(pYear, pMonth, pDay)

                // Selected expiration date
                var eYear = expiration.year
                var eMonth = expiration.month + 1 // Since January is 0
                var eDay = expiration.dayOfMonth
                val eDate = convertDate(eYear, eMonth, eDay)

                // Add product to database
                val db = DatabaseManager(requireActivity())
                val product = Food(name.text.toString(), pDate, eDate, amount.text.toString().toInt())
                db.insert(product)

                displayMessage.setText("Product: " + name.text.toString() + " successfully saved!\n" +
                        "Expiration Date: " + eDate
                )
            }

            // TODO Implement back button?
        }
    }

    /**
     * Function to validate the product name and category are not empty.
     */
    private fun validate(name: EditText, amount: EditText, category: EditText): Boolean {
        if (name.text.toString().isEmpty()) {
            name.error = "Name should not be blank."

            return false
        }

        if (amount.text.toString().isEmpty()) {
            amount.error = "Amount should not be blank."

            return false
        }

        if (category.text.toString().isEmpty()) {
            category.error = "Category should not be blank."

            return false
        }

        return true
    }

    /**
     * Convert date into one line of string.
     */
    private fun convertDate(year: Int, month: Int, day: Int): String {
        // yyyy-
        var date = "$year-"

        // yyyy-mm-
        // If month is 1 digit, concatenate a 0 before it
        if (month.toString().length == 1) {
            date = date.plus("0")
        }
        date = "$date$month-"

        // yyyy-mm-dd
        // If day is 1 digit, concatenate a 0 before it
        if (day.toString().length == 1) {
            date = date.plus("0")
        }
        date = "$date$day"

        return date
    }
}