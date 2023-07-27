package com.byteforce.kickash.ui.main.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HistoryAdapter(private val mList: List<HistoryData>) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(com.byteforce.kickash.R.layout.history_card, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val HistoryData = mList[position]

        // sets the image to the imageview from our itemHolder class
        holder.textView.text = HistoryData.date

        // sets the text to the textview from our itemHolder class
        holder.text_View.text = HistoryData.count.toString()

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView: TextView = itemView.findViewById(com.byteforce.kickash.R.id.HCdate)
        val text_View: TextView = itemView.findViewById(com.byteforce.kickash.R.id.HCcount)
    }
}
