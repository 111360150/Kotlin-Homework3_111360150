package com.example.lab7

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.GridView
import android.widget.ListView
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinner = findViewById<Spinner>(R.id.spinner)
        val listView = findViewById<ListView>(R.id.listView)
        val gridView = findViewById<GridView>(R.id.gridView)

        val items = mutableListOf<Item>()
        val counts = mutableListOf<String>()
        val priceRange = 10..100

        val array = resources.obtainTypedArray(R.array.image_list)
        for (i in 0 until array.length()) {
            val photo = array.getResourceId(i, 0)
            val name = "水果${i + 1}"
            val price = priceRange.random()
            items.add(Item(photo, name, price))
            counts.add("${i + 1}個")
        }
        array.recycle()

        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, counts)
        gridView.numColumns = 3
        gridView.adapter = MyAdapter(this, items, R.layout.adapter_vertical)
        listView.adapter = MyAdapter(this, items, R.layout.adapter_horizontal)

        // 新增點擊事件顯示價格
        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = items[position]
            Toast.makeText(this, "價格: ${selectedItem.price}元", Toast.LENGTH_SHORT).show()
        }
        gridView.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = items[position]
            Toast.makeText(this, "價格是 ${selectedItem.price}元", Toast.LENGTH_SHORT).show()
        }
    }
}
