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
import android.widget.TextView
import com.tfsg.surfeit.R
import com.tfsg.surfeit.ui.main.CameraFragment
import com.tfsg.surfeit.ui.main.ListFragment
import java.util.*

class HomeFragment : Fragment() {

    private val calendar:Calendar = Calendar.getInstance()
    private val currentDay:IntArray = intArrayOf(calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
    private var moneySaved = 68f;
    private var wasteAvoided = 18f;
    private var lastScanning:IntArray = intArrayOf(11, 7);
    private var scannedAmount = 23;

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(R.layout.home_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateMetrics(view)
        //finding button elements
        val cam = view.findViewById<Button>(R.id.cameraScanButton)
        val list = view.findViewById<Button>(R.id.categoryAdd)
        val c1 = view.findViewById<Button>(R.id.categoryButton1)
        val c2 = view.findViewById<Button>(R.id.categoryButton2)
        val c3 = view.findViewById<Button>(R.id.categoryButton3)
        val refresh = view.findViewById<Button>(R.id.refreshButton)
        //create callbacks for onclick
        cam?.setOnClickListener{
            setFrag(CameraFragment())
        }
        list?.setOnClickListener{
            setFrag(ListFragment())
        }
        c1?.setOnClickListener{
            setFrag(ListFragment()) //pass in category "Dairy"
        }
        c2?.setOnClickListener{
            setFrag(ListFragment()) //pass in category "Produce"
        }
        c3?.setOnClickListener{
            setFrag(ListFragment()) //pass in category "Leftovers"
        }
        refresh?.setOnClickListener {
            updateMetrics(view)
        }
    }

    private fun updateMetrics(view: View){
        //finding metric text elements
        val savings = view.findViewById<TextView>(R.id.savingsValue)
        val ml1 = view.findViewById<TextView>(R.id.metricLabel1)
        val ml2 = view.findViewById<TextView>(R.id.metricLabel2)
        val ml3 = view.findViewById<TextView>(R.id.metricLabel3)
        val mv1 = view.findViewById<TextView>(R.id.metricValue1)
        val mv2 = view.findViewById<TextView>(R.id.metricValue2)
        val mv3 = view.findViewById<TextView>(R.id.metricValue3)

        val savingValue = "$" + moneySaved
        savings?.text = savingValue
        //assign metric values
        ml1.text = "Food waste avoided:"
        ml2.text = "Last item scanned:"
        ml3.text = "Amount of items scanned:"
        mv1.text = "" + wasteAvoided + "lbs"
        val monthsSince = currentDay[0] - lastScanning[0]
        val daysSince = currentDay[1] - lastScanning[1]
        var lastScan = "" + lastScanning[0] + "/" + lastScanning[1] + " ("
        if (monthsSince > 0){
            lastScan += "" + monthsSince + " months and "
        }
        lastScan += "" + daysSince + " days ago)"
        mv2.text = lastScan
        mv3.text = "" + scannedAmount

        moneySaved += 1.25f
        wasteAvoided += 0.5f
        lastScanning = currentDay
        scannedAmount++
    }

    // set a fragment to be displayed
    private fun setFrag(fragment: Fragment) =
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
    }

}