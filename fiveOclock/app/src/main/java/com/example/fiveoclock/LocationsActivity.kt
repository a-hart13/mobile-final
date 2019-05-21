package com.example.fiveoclock

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_locations.*

class LocationsActivity : AppCompatActivity() {
    private val HOST_URL = "https://www.amdoren.com/time-zone-api/"
    companion object {
        val KEY_ITEM_TO_EDIT = "KEY_ITEM_TO_EDIT"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_locations)
        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            openDialog()
        }

    }
    private fun openDialog() {
        locationDialog().show(supportFragmentManager, "TAG_TODO_DIALOG")

    }

}
