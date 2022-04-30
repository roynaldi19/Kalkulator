package com.roynaldi19.kalkulator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.roynaldi19.kalkulator.room.History
import kotlinx.android.synthetic.main.item_layout.view.*

class HistoryAdapter(
    private val historys: ArrayList<History>,
    private val listener: onAdapterListener
) :
    RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    class HistoryViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val history = historys[position]
        val text = "${history.nilai1} ${history.operator} ${history.nilai2} = ${history.hasil}"
        holder.view.text_title.text = text

        holder.view.icon_delete.setOnClickListener {
            listener.onDelete(history)
        }
    }

    override fun getItemCount() = historys.size

    fun setData(list: List<History>) {
        historys.clear()
        historys.addAll(list)
        notifyDataSetChanged()
    }

    interface onAdapterListener {
        fun onDelete(history: History)
    }
}