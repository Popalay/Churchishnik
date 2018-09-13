package com.popalay.churchishnik.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.popalay.churchishnik.R
import com.popalay.churchishnik.bindView
import com.popalay.churchishnik.util.Api
import com.squareup.picasso.Picasso

class PointActivity : AppCompatActivity() {

    private val image: ImageView by bindView(R.id.image)
    private val textDescription: TextView by bindView(R.id.text_description)
    private val buttonAnswer: Button by bindView(R.id.button_answer)

    companion object {

        private const val EXTRA_POINT_INDEX = "EXTRA_POINT_INDEX"

        fun getIntent(context: Context, index: Int) = Intent(context, PointActivity::class.java).apply {
            putExtra(EXTRA_POINT_INDEX, index)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_point)
        val index = intent.getIntExtra(EXTRA_POINT_INDEX, -1)
        showPointInfo(index)
    }

    private fun showPointInfo(index: Int) {
        Api.fetchPoint(index) { point ->
            textDescription.text = point.description
            Picasso.get().load(point.imageLink).into(image)
        }
    }
}
