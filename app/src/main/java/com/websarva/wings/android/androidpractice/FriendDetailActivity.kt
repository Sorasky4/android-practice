package com.websarva.wings.android.androidpractice

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar

class FriendDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend_detail)

        val name = intent.getStringExtra("name")
        val location = intent.getStringExtra("location")
        val tvFriendName = findViewById<TextView>(R.id.tvFriendName)
        val tvFriendLocation = findViewById<TextView>(R.id.tvFriendLocation)
        tvFriendName.text = name
        tvFriendLocation.text = location
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}