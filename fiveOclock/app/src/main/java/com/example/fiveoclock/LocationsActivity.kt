package com.example.fiveoclock

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.View
import android.widget.AdapterView
import com.example.fiveoclock.DATA.AppDatabase
import com.example.fiveoclock.DATA.MyTimeZone
import com.example.fiveoclock.touch.ItemRecyclerTouchCallback
import kotlinx.android.synthetic.main.activity_locations.*
import kotlinx.android.synthetic.main.app_bar_main.*


class LocationsActivity : AppCompatActivity(), locationDialog.LocationHandler, AdapterView.OnItemSelectedListener {
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun locationCreated(timeZone: MyTimeZone) {
        Thread {
            var newId = AppDatabase.getInstance(this).itemDao().insertTimeZone(timeZone)

            timeZone.timeZoneId = newId

            runOnUiThread {
                itemAdapter.addItem(timeZone)
            }
        }.start()
    }

    lateinit var itemAdapter : ItemAdapter
    private val HOST_URL = "https://www.amdoren.com/time-zone-api/"
    companion object {
        val KEY_ITEM_TO_EDIT = "KEY_ITEM_TO_EDIT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_locations)
        setSupportActionBar(toolbar)
        newitem.setOnClickListener { view ->
            openDialog()
        }

        initRecyclerViewFromDB()

    }
    private fun openDialog() {
        locationDialog().show(supportFragmentManager, "TAG_TODO_DIALOG")

    }

    private fun initRecyclerViewFromDB() {
        Thread {
            var todoList = AppDatabase.getInstance(this@LocationsActivity).itemDao().getAllTimeZones()

            runOnUiThread {
                // Update UI

                itemAdapter = ItemAdapter(this, todoList)

                recyclerItem.layoutManager = LinearLayoutManager(this)

                recyclerItem.adapter = itemAdapter



                val callback = ItemRecyclerTouchCallback(itemAdapter)
                val touchHelper = ItemTouchHelper(callback)
                touchHelper.attachToRecyclerView(recyclerItem)
            }

        }.start()
    }

}
