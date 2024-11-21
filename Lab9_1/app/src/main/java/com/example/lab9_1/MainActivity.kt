package com.example.lab9_1

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.Button
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var progressRabbit = 0
    private var progressTurtle = 0
    private lateinit var btnStart: Button
    private lateinit var sbRabbit: SeekBar
    private lateinit var sbTurtle: SeekBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart = findViewById(R.id.btnStart)
        sbRabbit = findViewById(R.id.sbRabbit)
        sbTurtle = findViewById(R.id.sbTurtle)

        btnStart.setOnClickListener {
            startRace()
        }
    }

    private fun startRace() {
        btnStart.isEnabled = false
        progressRabbit = 0
        progressTurtle = 0
        sbRabbit.progress = 0
        sbTurtle.progress = 0
        runRabbit()
        runTurtle()
    }

    private fun resetRace() {
        progressRabbit = 0
        progressTurtle = 0
        sbRabbit.progress = 0
        sbTurtle.progress = 0
        btnStart.isEnabled = true
        showToast("賽程已重置")
    }

    private val handler = Handler(Looper.getMainLooper()) { msg ->
        when (msg.what) {
            1 -> {
                sbRabbit.progress = progressRabbit
                if (progressRabbit >= 100 && progressTurtle < 100) {
                    showToast("兔子勝利")
                    btnStart.isEnabled = true
                }
            }
            2 -> {
                sbTurtle.progress = progressTurtle
                if (progressTurtle >= 100 && progressRabbit < 100) {
                    showToast("烏龜勝利")
                    btnStart.isEnabled = true
                }
            }
        }
        true
    }

    private fun runRabbit() {
        Thread {
            val sleepChance = arrayOf(true, true, false)
            while (progressRabbit < 100 && progressTurtle < 100) {
                Thread.sleep(100)
                if (sleepChance.random()) Thread.sleep(300)
                progressRabbit += 3
                handler.sendMessage(Message().apply { what = 1 })
            }
        }.start()
    }

    private fun runTurtle() {
        Thread {
            while (progressTurtle < 100 && progressRabbit < 100) {
                Thread.sleep(100)
                progressTurtle += 1
                handler.sendMessage(Message().apply { what = 2 })
            }
        }.start()
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
