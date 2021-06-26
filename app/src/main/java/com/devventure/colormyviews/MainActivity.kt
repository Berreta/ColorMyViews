package com.devventure.colormyviews

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.drawable.toDrawable
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.ByteArrayOutputStream


class MainActivity : AppCompatActivity() {

    private lateinit var img: ImageView
    //val linear = findViewById<View>(R.id.mainContainer)
    private lateinit var linear: ConstraintLayout
    lateinit var sharedPreferences: SharedPreferences
    private val requestPermission = 1

    var color = R.color.gray
    val btn = findViewById<FloatingActionButton>(R.id.floatingActionButton)
    var boxes = arrayOf(R.id.box_one_text,
                        R.id.box_two_text,
                        R.id.box_three_text,
                        R.id.box_four_text,
                        R.id.box_five_text)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        linear = findViewById(R.id.imageView)

        sharedPreferences = getSharedPreferences("colors", Context.MODE_PRIVATE)

        for(tmp in boxes) {
            findViewById<TextView>(tmp).setBackgroundColor(sharedPreferences.getInt("$tmp" ,R.color.gray))
        }

        btn.setOnClickListener {

            var imgScreen = screenShot(linear)
            img.setImageBitmap(imgScreen)

            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "image/jpeg"
            val path = MediaStore.Images.Media.insertImage(contentResolver, imgScreen, "ScreenShot", null)
            val uri: Uri = Uri.parse(path)
            intent.putExtra(Intent.EXTRA_STREAM, uri)

            startActivity(Intent.createChooser(intent, "@String/shareMessage"))
        }

    }

        fun bttnClick(view: View) {
            this.color = when(view.id) {
                R.id.red_bttn -> R.color.red
                R.id.green_bttn -> R.color.green
                R.id.yellow_bttn -> R.color.yellow
                else -> R.color.gray
            }
        }

        fun boxClick(view: View) {
            view.setBackgroundColor(this.color)
            var id = view.id
            with (sharedPreferences.edit()) {
                putInt("$id",color)
                //commit()
                apply()
            }
        }

        fun screenShot(view: View): Bitmap {
            val bitmap = Bitmap.createBitmap(
                view.width, view.height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            view.draw(canvas)
            return bitmap
        }
}
