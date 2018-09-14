package com.popalay.churchishnik.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.RecyclerView
import com.popalay.churchishnik.R
import com.popalay.churchishnik.adapter.MessageAdapter
import com.popalay.churchishnik.bindView
import com.popalay.churchishnik.util.Api
import kotlin.properties.Delegates

class ChatActivity : AppCompatActivity() {

    private val listMessages: RecyclerView by bindView(R.id.list_messages)
    private var adapter: MessageAdapter by Delegates.notNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        adapter = MessageAdapter()
        listMessages.adapter = adapter
        listMessages.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL))
        listenMessages()
    }

    private fun listenMessages() {
        Api.fetchMessages {
            adapter.submitList(it)
        }
    }
}
