package com.example.lab8

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.widget.Toast

class MyAdapter(private val data: ArrayList<Contact>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private val tvName: TextView = v.findViewById(R.id.tvName)
        private val tvPhone: TextView = v.findViewById(R.id.tvPhone)
        private val imgDelete: ImageView = v.findViewById(R.id.imgDelete)

        fun bind(item: Contact, onDelete: (Contact) -> Unit, onLongClick: (Contact) -> Unit) {
            tvName.text = item.name
            tvPhone.text = item.phone
            imgDelete.setOnClickListener { onDelete(item) }
            itemView.setOnLongClickListener {
                onLongClick(item)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.adapter_row, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], {
            data.remove(it)
            notifyDataSetChanged()
        }, {
            Toast.makeText(holder.itemView.context, "Name: ${it.name}, Phone: ${it.phone}", Toast.LENGTH_SHORT).show()
        })
    }
}
