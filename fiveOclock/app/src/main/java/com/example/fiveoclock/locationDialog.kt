package com.example.fiveoclock


import android.app.Dialog
import android.content.Context
import android.net.wifi.WifiManager
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ArrayAdapter.createFromResource
import android.widget.EditText
import android.widget.Spinner
import com.example.fiveoclock.DATA.MyTimeZone
import kotlinx.android.synthetic.main.new_location_dialog.*
import kotlinx.android.synthetic.main.new_location_dialog.view.*

import java.lang.RuntimeException
import java.util.*





class locationDialog : DialogFragment(), AdapterView.OnItemSelectedListener {
    private lateinit var spinner: Spinner
    interface LocationHandler {
        fun locationCreated(timeZone: MyTimeZone)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        zone = parent?.getItemAtPosition(position).toString()

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }



    private lateinit var locationHandler: LocationHandler

    override fun onAttach(context: Context?) {
        super.onAttach(context)


        if (context is LocationHandler) {
            locationHandler = context
        } else {
            throw RuntimeException(
                "The activity does not implement the ItemHandlerInterface"
            )
        }
    }

    //private lateinit var etTodoDate: EditText
    private lateinit var etItemText: EditText
    private lateinit var etFriendsText: EditText
    private lateinit var zone: String
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val rootView = requireActivity().layoutInflater.inflate(
            R.layout.new_location_dialog, null
        )
        //var timezones: Array<String> = TimeZone.getAvailableIDs()

        val builder = AlertDialog.Builder(requireContext())
        spinner = rootView.spinner
        builder.setTitle("New item")

        //etTodoDate = rootView.findViewById(R.id.etTodoText)
        // etTodoDate = rootView.etDate
        etItemText = rootView.etItem
        etFriendsText = rootView.etFriends
        zone=""
        builder.setView(rootView)

        val zonesAdapter = createFromResource(
            activity,
            R.array.Timezone,
            android.R.layout.simple_spinner_item)
        zonesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = zonesAdapter
        spinner.onItemSelectedListener = this

        val arguments = this.arguments

        // IF I AM IN EDIT MODE
        if (arguments != null && arguments.containsKey(
                LocationsActivity.KEY_ITEM_TO_EDIT
            )
        ) {

            val item = arguments.getSerializable(
                LocationsActivity.KEY_ITEM_TO_EDIT
            ) as MyTimeZone

            //  etTodoDate.setText(todoItem.createDate)
            etItemText.setText(item.cityName)


            builder.setTitle("Edit item")
        }

        builder.setPositiveButton("OK") { dialog, witch ->
            // empty
        }

        return builder.create()
    }
    override fun onResume() {

        super.onResume()




        val positiveButton = (dialog as AlertDialog).getButton(Dialog.BUTTON_POSITIVE)
        positiveButton.setOnClickListener {
            if (etItemText.text.isNotEmpty()) {
                val arguments = this.arguments

                handleItemCreate()

                dialog.dismiss()

            }
        }
    }
    private fun handleItemCreate() {

        locationHandler.locationCreated(
            MyTimeZone(
                null,

                etItemText.text.toString(),
                etFriendsText.text.toString(),
                zone


            )
        )
    }


}

