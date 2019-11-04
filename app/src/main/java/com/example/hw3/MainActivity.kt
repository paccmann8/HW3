package com.example.hw3

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        camera_button.setOnClickListener { _ ->
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent, 777)
        }

        display_person_button.setOnClickListener { _ ->
            val name = name_edittext.text.toString()

            val myHuman = Human(name, "Black/Brown")

            val humanIntent = Intent(this, HumanActivity::class.java)
            humanIntent.putExtra("my_human_key", myHuman)
            startActivity(humanIntent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 777 && resultCode == Activity.RESULT_OK) {
            val bitmap: Bitmap = (data?.extras?.get("data") as Bitmap)
            my_imageview.setImageBitmap(bitmap)
        }
    }
}