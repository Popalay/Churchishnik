package com.popalay.churchishnik.adapter

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.popalay.churchishnik.R
import com.popalay.churchishnik.bindView
import com.popalay.churchishnik.model.Message

class MessageAdapter : ListAdapter<Message, MessageAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Message>() {

            override fun areItemsTheSame(old: Message, new: Message): Boolean = old == new

            override fun areContentsTheSame(old: Message, new: Message): Boolean = old == new
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_message, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(getItem(position))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val textMessage: TextView by bindView(R.id.text_message)

        fun bind(message: Message) {
            textMessage.text = message.text
        }
    }
}