package com.route.gpstrackerc39_gsat

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.CurrentLocationRequest
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions


class MainActivity : AppCompatActivity() {
    // runtime permission
    val registerForPermissionCallback =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGrant ->
            if (isGrant) {
                // Access User Location
                getUserLocation()
            } else {
                //
                Toast.makeText(this, "Permission Not Granted", Toast.LENGTH_LONG).show()
            }
        }
    var googleMap: GoogleMap? = null
    var marker: Marker? = null
    lateinit var fusedLocationProvider: FusedLocationProviderClient
    fun requestLocationPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                // You can use the API that requires the permission.
                getUserLocation()
            }

            ActivityCompat.shouldShowRequestPermissionRationale(
                this, android.Manifest.permission.ACCESS_FINE_LOCATION
            ) -> {
                // Show Dialog
                // In an educational UI, explain to the user why your app requires this
                // permission for a specific feature to behave as expected, and what
                // features are disabled if it's declined. In this UI, include a
                // "cancel" or "no thanks" button that lets the user continue
                // using your app without granting the permission.
//                showInContextUI(...)
                showDialog(
                    context = this,
                    message = "We need location Permission to find Nearest drivers",
                    positiveButtonText = "Show Again",
                    onPositiveButtonClick = {
                        registerForPermissionCallback.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
                    },
                    negativeButtonText = "Cancel"
                )
            }

            else -> {
                // You can directly ask for the permission.
                // The registered ActivityResultCallback gets the result of this request.
                registerForPermissionCallback.launch(
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                )
            }
        }
    }


    @SuppressLint("MissingPermission")
    fun getUserLocation() {
        // get User Location
        // Location Manager -> Location
        // FusedLocation Provider
        fusedLocationProvider = LocationServices.getFusedLocationProviderClient(this)
        val currentLocationRequest = CurrentLocationRequest
            .Builder()
            .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
            .build()
        fusedLocationProvider
            .getCurrentLocation(currentLocationRequest, null)
            .addOnSuccessListener {
                Log.e("Location TAG", "getUserLocation latitude = : ${it.latitude}")
                Log.e("Location TAG", "getUserLocation longitude = : ${it.longitude}")
            }.addOnFailureListener {
                Log.e("Location TAG", "getUserLocation:Error -> ${it.message} ")
            }
        val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 5000).build()
        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                Log.e(
                    "TAG",
                    "onLocationResult: Latitude : ${locationResult.lastLocation?.latitude}"
                )
                Log.e(
                    "TAG",
                    "onLocationResult: Longitude : ${locationResult.lastLocation?.longitude}"
                )
                putMarkerOnMap(
                    locationResult.lastLocation?.latitude ?: 0.0,
                    locationResult.lastLocation?.longitude ?: 0.0
                )
            }
        }
        // Double Ctrl
        fusedLocationProvider.requestLocationUpdates(
            locationRequest,
            locationCallback,
            // Multi Threading -> Looper
            Looper.getMainLooper()
        )
        // findFragmentById
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync { googleMap ->
//            googleMap.
            // put Marker on Map Corresponding to user location
            this.googleMap = googleMap
        }
    }

    private fun bitmapDescriptorFromVector(
        context: Context,
        @DrawableRes vectorResId: Int
    ): BitmapDescriptor? {
        val vectorDrawable = ContextCompat.getDrawable(context, vectorResId)
        vectorDrawable!!.setBounds(
            0,
            0,
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight
        )
        val bitmap = Bitmap.createBitmap(
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        vectorDrawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }

    fun putMarkerOnMap(latitude: Double, longitude: Double) {
        if (marker == null) {
            val markerOptions = MarkerOptions()
            markerOptions.title("This is Driver Icon")
            markerOptions.position(LatLng(latitude, longitude))
//        markerOptions.icon(bitmapDescriptorFromVector(this, R.drawable.ic_android))
            marker = googleMap?.addMarker(markerOptions)
        }
        marker?.position = LatLng(latitude, longitude)              // 4F -> 21F
        googleMap?.animateCamera(
            CameraUpdateFactory.newLatLngZoom(
                LatLng(latitude, longitude),
                16F
            )
        )
        // SOLID
        // 'S'ingle Responsibility Principle
        // 'O'pen Closed principle
        // 'L'iskov Substution principle
        // 'I'nterface Segregation
        // 'D'ependency Inversion
    }

    interface OnClickListener {
        fun onClick(view: View)
        fun onSwipe(view: View)
        fun onLongClickListener(view: View)
    }

    data class Product(
        val id: String,
        val name: String
    ) {
        fun showProductDetails() {
            Log.e("TAG", "showProductDetails: $id  , $name")
        }

    }

    class ReceiptPrinter {

        fun printReceipt() {
            Log.e("Tag", "printReceipt: ")
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //recycler View
        // OnItemClickListener  -  Lambda Expression
        requestLocationPermission()
    }
    // Google Maps  -> SOLID -> Creational Design Patterns
    // To Do App ->
}