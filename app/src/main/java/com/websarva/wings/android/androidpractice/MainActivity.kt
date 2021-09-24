package com.websarva.wings.android.androidpractice

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // WalkTroughActivity.showIfNeeded(this, savedInstanceState)
        WalkTroughActivity.showForcibly(this)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.setLogo(R.mipmap.ic_launcher)
        toolbar.setTitle(R.string.toolbar_title)
        toolbar.setTitleTextColor(Color.WHITE)
        toolbar.setSubtitle(R.string.toolbar_subtitle1)
        toolbar.setSubtitleTextColor(Color.LTGRAY)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val lvFriend = findViewById<ListView>(R.id.lvFriend)
        val friendsList: MutableList<MutableMap<String, Any>> = mutableListOf()
        var friend: MutableMap<String, Any> = mutableMapOf("name" to "そら", "location" to "講堂", "status" to getString(R.string.status_online), "icon" to R.drawable.sora_icon)
        friendsList.add(friend)
        friend = mutableMapOf("name" to "maki", "location" to "大講義室", "status" to getString(R.string.status_free))
        friendsList.add(friend)
        friend = mutableMapOf("name" to "あみみ", "location" to "エレ工", "status" to getString(R.string.status_busy))
        friendsList.add(friend)
        friend = mutableMapOf("name" to "t4t5u0", "location" to "595教室", "status" to getString(R.string.status_offline))
        friendsList.add(friend)
        val from = arrayOf("name", "location", "status", "icon")
        val to = intArrayOf(R.id.friendName, R.id.friendLocation, R.id.status, R.id.icon)
        val adapter = SimpleAdapter(applicationContext, friendsList, R.layout.inline_list_item_2, from, to)
        lvFriend.adapter = adapter
        // リストタップのリスナクラス登録
        lvFriend.onItemClickListener = ListItemClickListener()
    }

    // リストがタップされたときの処理が記述されたメンバクラス
    private inner class ListItemClickListener: AdapterView.OnItemClickListener{
        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            val item = parent.getItemAtPosition(position) as MutableMap<String, String>
            val name = item["name"]
            val location = item["location"]
            // ダイアログフラグメントオブジェクトの生成
            val dialogFragment = DisplayConfirmDialogFragment()
            val args = Bundle()
            args.putString("name", name)
            args.putString("location", location)
            dialogFragment.arguments = args
            dialogFragment.show(supportFragmentManager, "DisplayConfirmDialogFragment")
        }
    }
}