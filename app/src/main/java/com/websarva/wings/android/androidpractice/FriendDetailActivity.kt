package com.websarva.wings.android.androidpractice

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar

class FriendDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend_detail)

        val name = intent.getStringExtra("name")
        val location = intent.getStringExtra("location")
        val status: String? = intent.getStringExtra("status")
        val icon = intent.getIntExtra("icon", R.drawable.ic_baseline_mood_24)
        val tvFriendName = findViewById<TextView>(R.id.tvFriendName)
        val tvFriendLocation = findViewById<TextView>(R.id.tvFriendLocation)
        val tvFriendStatus = findViewById<TextView>(R.id.tvFriendStatus)
        val tvFriendIcon = findViewById<ImageView>(R.id.ivFriendIcon)
        tvFriendName.text = name
        tvFriendLocation.text = location
        tvFriendStatus.text = status
        tvFriendIcon?.setImageResource(icon)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}