package com.example.informationsecurity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lcodecore.extextview.ExpandTextView
import kotlinx.android.synthetic.main.recycler_view_item_button.view.*

class HomeRecyclerViewAdapter(
    private val stringArray: Array<String>,
    private val infoStringArray: Array<String>,
    private val onButtonClick: OnButtonClick
) : RecyclerView.Adapter<HomeRecyclerViewAdapter.ButtonViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ButtonViewHolder {
        return ButtonViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_view_item_button, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ButtonViewHolder, position: Int) {
        holder.textViewName.text = stringArray[position]
        holder.textViewName.setOnClickListener {
            onButtonClick.onClick(position)
        }
        holder.textViewInfo.text = infoStringArray[position]
    }

    override fun getItemCount() = stringArray.size

    class ButtonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewName: TextView = itemView.textViewName
        val textViewInfo: ExpandTextView = itemView.textViewInfo
    }

    interface OnButtonClick {
        fun onClick(position: Int)
    }

}
