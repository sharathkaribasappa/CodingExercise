package com.codingexercise.view

import android.Manifest
import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.codingexercise.R
import com.codingexercise.service.model.Atm
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

const val PARAM_ATM = "param_atm"
const val LOCATION_PERMISSION: Int = 1

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var atm: Atm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        atm = intent.extras.get(PARAM_ATM) as Atm

        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        if (!hasLocationPermission(applicationContext)) {
            requestLocationPermission(this)
        } else {
            mMap?.isMyLocationEnabled = true
        }

        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.isZoomGesturesEnabled = true
        addMarkers(mMap)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    /**
     * react to the user tapping the back/up icon in the action bar
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.getItemId()) {
            android.R.id.home -> {
                // this takes the user 'back', as if they pressed the left-facing triangle icon on the main android toolbar.
                // if this doesn't work as desired, another possibility is to call `finish()` here.
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun addMarkers(map: GoogleMap?) {
        atm.location.let { location ->
            var storeLocation =
                LatLng(
                    location.lat,
                    location.lng
                )
            map?.addMarker(
                MarkerOptions()
                    .position(storeLocation)
                    .title(atm.name)
                    .icon(
                        BitmapDescriptorFactory.fromBitmap(
                            BitmapFactory.decodeResource(resources, R.drawable.marker_atm_commbank)
                        )
                    )
            )
            mMap.moveCamera(CameraUpdateFactory.newLatLng(storeLocation))
            mMap.animateCamera(CameraUpdateFactory.zoomTo(16.0f))
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun hasLocationPermission(context: Context): Boolean {
        return  ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun requestLocationPermission(activity: Activity) {
        val permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
        activity.requestPermissions(permissions, LOCATION_PERMISSION)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == LOCATION_PERMISSION && grantResults.isNotEmpty()) {
            mMap?.isMyLocationEnabled = true
        }
    }
}
