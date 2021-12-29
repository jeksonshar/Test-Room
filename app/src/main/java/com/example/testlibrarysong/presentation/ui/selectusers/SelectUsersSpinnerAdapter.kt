package com.example.testlibrarysong.presentation.ui.selectusers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.testlibrarysong.business.domain.User

class SelectUsersSpinnerAdapter(val context: Context, var data: List<User>) : BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(position: Int): Any {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return data[position].id.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val viewHolder: ItemHolder
        if (convertView == null) {
            view = inflater.inflate(android.R.layout.simple_spinner_item , parent, false)
            viewHolder = ItemHolder(view)
            view?.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ItemHolder
        }
        val tmp = data[position].id.toString() + " " + data[position].firstName
        viewHolder.label.text = tmp
        return view
    }

    private class ItemHolder(view: View?) {
        val label: TextView = view?.findViewById(android.R.id.text1) as TextView
    }

}