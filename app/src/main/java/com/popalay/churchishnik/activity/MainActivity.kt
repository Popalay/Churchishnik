package com.popalay.churchishnik.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.popalay.churchishnik.R
import com.popalay.churchishnik.bindView
import com.popalay.churchishnik.util.Api

class MainActivity : AppCompatActivity() {

    private val buttonStart: Button by bindView(R.id.button_start)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!Api.isFirsStart()) {
            startActivity(Intent(this, MapActivity::class.java))
            finish()
        }

        buttonStart.setOnClickListener {
            startActivity(Intent(this, MapActivity::class.java))
            finish()
        }
    }
}