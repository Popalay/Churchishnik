package com.popalay.churchishnik.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.popalay.churchishnik.R
import com.popalay.churchishnik.adapter.ImageSliderAdapter
import com.popalay.churchishnik.bindView
import com.popalay.churchishnik.util.Api
import com.viewpagerindicator.CirclePageIndicator

class PointActivity : AppCompatActivity() {

    private val inputAnswer: EditText by bindView(R.id.input_answer)
    private val pagerIndicator: CirclePageIndicator by bindView(R.id.pager_indicator)
    private val pagerImages: ViewPager by bindView(R.id.pager_images)
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
            val links = point.imageLink.split(",")
            pagerImages.adapter = ImageSliderAdapter(this, links)
            pagerIndicator.setViewPager(pagerImages)
            buttonAnswer.setOnClickListener {
                val answer = inputAnswer.text.trim().toString()
                if (answer == point.answer) {
                    Toast.makeText(this, "Неужели угадал? Ну го дальше по говну", Toast.LENGTH_LONG).show()
                    Api.saveLastPoint(point.index)
                    finish()
                } else {
                    inputAnswer.text = null
                    Toast.makeText(this, "Шукай дали, а потом тупани еще раз...", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
