package com.popalay.churchishnik.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso

class ImageSliderAdapter(
        private val context: Context,
        private val imageList: List<String>
) : PagerAdapter() {

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view === `object`

    override fun getCount(): Int = imageList.size

    override fun instantiateItem(collection: ViewGroup, position: Int): Any {
        val imageLayout = ImageView(context).apply {
            Picasso.get().load(imageList[position]).into(this)
            scaleType = ImageView.ScaleType.CENTER_CROP
        }
        collection.addView(imageLayout)
        return imageLayout
    }

    override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {
        collection.removeView(view as View)
    }
}