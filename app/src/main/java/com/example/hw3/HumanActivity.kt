package com.example.hw3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class HumanActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_human)

        val humanIn :Human = intent?.getSerializableExtra(  "my_human_key") as Human

    }
}
