package com.example.fiveoclock

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fiveoclock.DATA.AppDatabase
import com.example.fiveoclock.DATA.MyTimeZone
import com.example.fiveoclock.touch.ItemTouchHelperCallback

import kotlinx.android.synthetic.main.activity_locations.*
import kotlinx.android.synthetic.main.item_row.view.*
import kotlinx.android.synthetic.main.new_location_dialog.view.*
import java.sql.Time
import java.util.*

class ItemAdapter : RecyclerView.Adapter<ItemAdapter.ViewHolder>, ItemTouchHelperCallback{


    override fun onDismissed(position: Int) {
        //deleteItem(position)
    }

    override fun onItemMoved(fromPosition: Int, toPosition: Int) {
        Collections.swap(items, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }
    var items = mutableListOf<MyTimeZone>()

    private val context: Context



    constructor(context: Context, listItems: List<MyTimeZone>) : super() {
        this.context = context
        items.addAll(listItems)


    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        val todoRowView = LayoutInflater.from(context).inflate(
            R.layout.item_row, viewGroup, false
        )
        return ViewHolder(todoRowView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item  = items.get(position)

        viewHolder.clock.timeZone = item.zone

        viewHolder.city.text=item.cityName
        viewHolder.friends.text=item.friends





    }

    fun updateItem(item: MyTimeZone) {
        Thread{
            AppDatabase.getInstance(context).itemDao().updateTimeZone(item)


        }.start()
    }

    fun updateItem(item: MyTimeZone, editIndex: Int) {
        items.set(editIndex, item)
        notifyItemChanged(editIndex)
    }


    fun addItem(item: MyTimeZone) {
        items.add(0, item)
        //notifyDataSetChanged()
        notifyItemInserted(0)
    }




    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var clock = itemView.clock
        var city = itemView.city
        var friends = itemView.friends
    }

}