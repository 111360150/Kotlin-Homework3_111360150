package com.example.lab8

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SecActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sec)

        val edName = findViewById<EditText>(R.id.edName)
        val edPhone = findViewById<EditText>(R.id.edPhone)
        val btnSend = findViewById<Button>(R.id.btnSend)
        val btnCancel = findViewById<Button>(R.id.btnCancel)

        // 確認新增按鈕
        btnSend.setOnClickListener {
            when {
                edName.text.isEmpty() -> showToast("請輸入姓名")
                edPhone.text.isEmpty() -> showToast("請輸入電話")
                else -> {
                    setResult(Activity.RESULT_OK, Intent().apply {
                        putExtra("name", edName.text.toString())
                        putExtra("phone", edPhone.text.toString())
                    })
                    finish()
                }
            }
        }

        // 取消按鈕
        btnCancel.setOnClickListener {
            setResult(Activity.RESULT_CANCELED) // 回傳取消狀態
            finish()
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
