package com.example.hbv601g_t8

import ChatAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.button.MaterialButton
import java.time.ZonedDateTime
import com.example.hbv601g_t8.Message

class ChatActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var sendButton: MaterialButton
    private lateinit var messageInput: TextInputEditText

    private val currentUserId = 1.toLong()

    private var counter = 7.toLong()

    private var message1 = Message(1, "halló", 1, 2, ZonedDateTime.now(), true)
    private var message2 = Message(2, "hæ", 1, currentUserId, ZonedDateTime.now(), true)
    private var message3 = Message(3, ":)", 1, 2, ZonedDateTime.now(), false)
    private var message4 = Message(4, "Má ég plís fá fribsídiskinn", 2, 3, ZonedDateTime.now(), true)
    private var message5 = Message(5, "100kr?", 3, currentUserId, ZonedDateTime.now(), true)
    private var message6 = Message(6, "nei", 3, 4, ZonedDateTime.now(), false)

    private var dummyMessages = arrayListOf<Message>(message1, message2, message3, message4, message5, message6)

    private var chatMessages = arrayListOf<Message>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val chatId = intent.getLongExtra("CHAT_ID", -1) // Default value as -1
        if (chatId == (-1).toLong()) {
            println("skoða seinna")
        }
        chatMessages = dummyMessages.filter {it.conversationId == chatId} as ArrayList<Message>
        //TODO: Sækja uppl. frá bakenda fyrir chatId
        recyclerView = findViewById<RecyclerView>(R.id.chatRecyclerView).apply {
            layoutManager = LinearLayoutManager(this@ChatActivity)
            adapter = ChatAdapter(chatMessages, currentUserId)
        }

        sendButton = findViewById(R.id.sendButton)
        messageInput = findViewById(R.id.messageInput)

        sendButton.setOnClickListener {
            val messageText = messageInput.text.toString()

            if (messageText.isNotEmpty()) {
                val newMessage = Message(counter++, messageText, 1, currentUserId, ZonedDateTime.now(), true)
                dummyMessages.add(newMessage)
                chatMessages.add(newMessage)
                (recyclerView.adapter as? ChatAdapter)?.notifyItemInserted(chatMessages.size - 1)
                recyclerView.scrollToPosition(chatMessages.size - 1)  // Scroll to the new message
                messageInput.text?.clear()
                // TODO: Implement the logic to send the message to the backend and update the UI accordingly
            }

        }
    }


}
