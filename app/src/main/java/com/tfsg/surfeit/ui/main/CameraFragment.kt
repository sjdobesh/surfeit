package com.tfsg.surfeit.ui.main

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.*
import com.tfsg.surfeit.R
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

private const val CAMERA_REQUEST_CODE = 101

class CameraFragment : Fragment() {
    private lateinit var codeScanner: CodeScanner

    companion object {
        fun newInstance() = CameraFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        setupPermissions() // Once application is booted up, camera permissions will be asked

        return inflater.inflate(R.layout.camera_fragment, container, false)
    }

    /**
     * Code referenced from https://github.com/yuriy-budiyev/code-scanner
     * Main function to continuously have the camera on to scan barcodes.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val scannerView = view.findViewById<CodeScannerView>(R.id.scanner_view)
        val activity = requireActivity()
        var lastBarcode = ""
        var scanCount = 0

        codeScanner = CodeScanner(activity, scannerView)

        // Code from https://www.youtube.com/watch?v=drH63NpSWyk
        // Camera settings
        codeScanner.apply {
            camera = CodeScanner.CAMERA_BACK
            formats = CodeScanner.ALL_FORMATS

            autoFocusMode = AutoFocusMode.SAFE
            scanMode = ScanMode.CONTINUOUS
            isAutoFocusEnabled = true
            isFlashEnabled = false

            // Display the scanned barcode
            codeScanner.decodeCallback = DecodeCallback {
                activity.runOnUiThread {
                    val scannedItem = view.findViewById<TextView>(R.id.tv_textView)

                    scannedItem.text = it.text
                    if(it.text != lastBarcode) {
                        scanCount++
                        val scanCounter = view.findViewById<TextView>(R.id.textView)
                        scanCounter.text = "" + scanCount
                        lastBarcode = it.text
                        // TODO Save the decoded barcode with it.text
                        val scannerView = view.findViewById<TextView>(R.id.tv_scannedName)
                        val scannerDesc = view.findViewById<TextView>(R.id.tv_scannedDesc)

                        //tutorial from https://developer.android.com/training/volley/simple

                        // Instantiate the RequestQueue.
                        val queue = Volley.newRequestQueue(getActivity()?.getApplicationContext())
                        val url = "https://api.upcitemdb.com/prod/trial/lookup?upc=" + it.text

                        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
                            Response.Listener { response ->
//                                scannerView.text = response.getString("code")
                                val jsonArray = response.getJSONArray("items")
                                if (jsonArray.length() > 0) {
                                    val item = jsonArray.getJSONObject(0);
                                    val title = item.getString("title")
                                    val desc = item.getString("description")
                                    var titleLength = 50;
                                    if (title.length < titleLength) titleLength = title.length;
                                    scannerView.text = "%s".format(title.substring(0, titleLength))
                                    var descLength = 85;
                                    if (desc.length < descLength) descLength = desc.length;
                                    scannerDesc.text = "%s".format(desc.substring(0, descLength))
                                }
                            },
                            Response.ErrorListener { error ->
                                scannerView.text = "Invalid Scan"
                                scannerDesc.text = ""
                            }
                        )
                        // Add the request to the RequestQueue.
                        queue.add(jsonObjectRequest)

                    }
                }
            }
        }

        scannerView.setOnClickListener {
            codeScanner.startPreview()
        }
    }

    /**
     * Code from https://github.com/yuriy-budiyev/code-scanner
     * Lifecycle method which makes application do what it usually does.
     * When user re-enters the application, camera will try to fetch new barcode.
     */
    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    /**
     * Code from https://github.com/yuriy-budiyev/code-scanner
     * Exiting application releases resources which avoids memory leaks.
     */
    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }

    /**
     * Code from https://www.youtube.com/watch?v=drH63NpSWyk
     * Checks camera permissions. If not granted, attempt to ask user for permission.
     */
    private fun setupPermissions() {
        val permission = ContextCompat.checkSelfPermission(requireActivity(), android.Manifest.permission.CAMERA)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            makeRequest()
        }
    }

    /**
     * Code from https://www.youtube.com/watch?v=drH63NpSWyk
     * Request camera permissions.
     */
    private fun makeRequest() {
        ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.CAMERA), CAMERA_REQUEST_CODE)
    }

    /**
     * Code from https://www.youtube.com/watch?v=drH63NpSWyk
     * If the permission is not granted (denied), display message that it is required.
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            CAMERA_REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(requireActivity(), "Camera permission must be granted to be able to use this application.", Toast.LENGTH_SHORT)
                }
            }
        }
    }
}