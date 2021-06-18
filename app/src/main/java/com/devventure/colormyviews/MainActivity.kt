package com.devventure.colormyviews

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var changeColor = R.color.gray
        var redBtn =    findViewById<Button>(R.id.red_bttn)
        var yellowBtn = findViewById<Button>(R.id.yellow_bttn)
        var greenBtn =  findViewById<Button>(R.id.green_bttn)

        redBtn.setOnClickListener {
            changeColor = R.color.red
        }

        yellowBtn.setOnClickListener {
            changeColor = R.color.yellow
        }

        greenBtn.setOnClickListener {
            changeColor = R.color.green
        }



    }

    /*override fun onStop() {
        super.onStop()
        val sharedPreferences = getPreferences("colors",Context.MODE_PRIVATE)
        val editor = getPreferences().edit()
        //editor.putInt("boxOne")
    }*/
}